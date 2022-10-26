package br.com.challenge.votingsessionmanager.core.votes.usecase;

import java.util.Objects;

import br.com.challenge.votingsessionmanager.core.exception.BusinessRuleException;
import br.com.challenge.votingsessionmanager.core.exception.UnexpectedErrorException;
import br.com.challenge.votingsessionmanager.core.session.domain.Session;
import br.com.challenge.votingsessionmanager.core.session.mapper.SessionMapper;
import br.com.challenge.votingsessionmanager.core.session.port.FindSessionOutputPort;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.CreateVoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import br.com.challenge.votingsessionmanager.core.votes.mapper.VoteMapper;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteInputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteOutputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.FindVoteOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateVoteUseCase implements CreateVoteInputPort {
    private final VoteMapper voteMapper;
    private final SessionMapper sessionMapper;
    private final FindSessionOutputPort findSessionOutputPort;
    private final FindVoteOutputPort findVoteOutputPort;
    private final CreateVoteOutputPort createVoteOutputPort;

    @Override
    public Mono<VoteDataTransfer> createVote(@NonNull final CreateVoteDataTransfer createVoteDataTransfer) {
        log.info("Creating vote: " + createVoteDataTransfer);

        return Mono.just(createVoteDataTransfer)
                .flatMap(this::validateSessionOpen)
                .map(voteMapper::createVoteDataTransferToVote)
                .flatMap(this::validateAffiliateVote)
                .flatMap(createVoteOutputPort::create)
                .log()
                .onErrorMap(this::defineError);
    }

    private Mono<CreateVoteDataTransfer> validateSessionOpen(final CreateVoteDataTransfer createVoteDataTransfer) {
        return Mono.just(createVoteDataTransfer)
                .map(CreateVoteDataTransfer::getSessionId)
                .flatMap(findSessionOutputPort::findById)
                .map(sessionMapper::sessionDataTransferToSession)
                .filter(Session::isSessionOpen)
                .map(s -> createVoteDataTransfer)
                .switchIfEmpty(Mono.error(new BusinessRuleException("The session is closed")));
    }

    private Mono<VoteDataTransfer> validateAffiliateVote(final VoteDataTransfer voteDataTransfer) {
        return findVoteOutputPort.findBySessionIdAffiliateId(voteDataTransfer.getSessionId(),
                                                             voteDataTransfer.getAffiliateId())
                .hasElement()
                .filter(Boolean.FALSE::equals)
                .map(b -> voteDataTransfer)
                .switchIfEmpty(Mono.error(new BusinessRuleException("The affiliate already voted")));
    }

    private Throwable defineError(final Throwable exception) {
        if (exception instanceof BusinessRuleException) {
            return exception;
        }

        return new UnexpectedErrorException("Unexpected error when create topic", exception);
    }
}

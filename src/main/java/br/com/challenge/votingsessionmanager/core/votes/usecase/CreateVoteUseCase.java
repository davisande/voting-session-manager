package br.com.challenge.votingsessionmanager.core.votes.usecase;

import br.com.challenge.votingsessionmanager.core.exception.BusinessRuleException;
import br.com.challenge.votingsessionmanager.core.exception.UnexpectedErrorException;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import br.com.challenge.votingsessionmanager.core.votes.mapper.VoteMapper;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteInputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteOutputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.FindVoteOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateVoteUseCase implements CreateVoteInputPort {
    private final VoteMapper voteMapper;
    private final FindVoteOutputPort findVoteOutputPort;
    private final CreateVoteOutputPort createVoteOutputPort;

    @Override public Mono<VoteDataTransfer> createVote(final VoteDataTransfer voteDataTransfer) {
        log.info("Creating vote: " + voteDataTransfer);

        return Mono.just(voteDataTransfer)
                .map(voteMapper::voteDataTransferToVote)
                .flatMap(this::validateAffiliateVote)
                .flatMap(createVoteOutputPort::create)
                .map(voteMapper::voteToVoteDataTransfer)
                .log()
                .onErrorMap(this::defineError);
    }

    private Mono<Vote> validateAffiliateVote(final Vote vote) {
        return Mono.just(vote.getAffiliateId())
                .flatMap(findVoteOutputPort::findByAffiliateId)
                .map(this::performValidation)
                .map(v -> vote)
                .log()
                .onErrorMap(e -> new BusinessRuleException("Affiliate already voted"));
    }

    private Vote performValidation(final Vote vote) {
        if (!vote.isValidVote()) {
            throw new BusinessRuleException("Affiliate already voted");
        }

        return vote;
    }

    private Throwable defineError(final Throwable exception) {
        if (exception instanceof BusinessRuleException) {
            return exception;
        }

        return new UnexpectedErrorException("Unexpected error when create topic", exception);
    }
}

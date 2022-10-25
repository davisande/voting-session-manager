package br.com.challenge.votingsessionmanager.core.votes.usecase;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import br.com.challenge.votingsessionmanager.core.exception.BusinessRuleException;
import br.com.challenge.votingsessionmanager.core.exception.UnexpectedErrorException;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteCountedDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteResultDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import br.com.challenge.votingsessionmanager.core.votes.domain.VoteResult;
import br.com.challenge.votingsessionmanager.core.votes.mapper.VoteMapper;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteInputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteOutputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.FindVoteOutputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.VoteResultInputPort;
import br.com.challenge.votingsessionmanager.core.votes.port.VoteResultOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class VoteResultUseCase implements VoteResultInputPort {
    private final VoteMapper voteMapper;
    private final VoteResultOutputPort voteResultOutputPort;

    @Override
    public Mono<VoteResultDataTransfer> calculateVotes(@NonNull final Integer sessionId) {
        log.info("Calculating vote: [sessionId: %s]", sessionId);

        return Mono.just(sessionId)
                .flatMap(voteResultOutputPort::getAmountVotes)
                .map(this::performVoteCalculation)
                .map(voteMapper::voteResultToVoteResultDataTransfer)
                .log()
                .onErrorMap(e -> new UnexpectedErrorException("Unexpected error when calculate votes", e));
    }

    private VoteResult performVoteCalculation(final List<VoteCountedDataTransfer> votesCounted) {
        final VoteResult voteResult = VoteResult.builder()
                .votesCounted(votesCounted)
                .build();

        voteResult.calculateVoteResult();

        return voteResult;
    }
}

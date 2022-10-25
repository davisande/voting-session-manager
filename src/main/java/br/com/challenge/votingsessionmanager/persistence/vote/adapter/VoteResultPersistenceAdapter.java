package br.com.challenge.votingsessionmanager.persistence.vote.adapter;

import java.util.List;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteCountedDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.port.VoteResultOutputPort;
import br.com.challenge.votingsessionmanager.persistence.vote.VotePersistenceMapper;
import br.com.challenge.votingsessionmanager.persistence.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class VoteResultPersistenceAdapter implements VoteResultOutputPort {
    private final VotePersistenceMapper votePersistenceMapper;
    private final VoteRepository voteRepository;

    @Override
    public Mono<List<VoteCountedDataTransfer>> getAmountVotes(final Integer sessionId) {
        return Mono.just(sessionId)
                .map(voteRepository::countVotesBySession)
                .map(votePersistenceMapper::voteResultEntityToVoteCountedDataTransfer)
                .log();
    }
}

package br.com.challenge.votingsessionmanager.persistence.vote.adapter;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteOutputPort;
import br.com.challenge.votingsessionmanager.persistence.vote.VotePersistenceMapper;
import br.com.challenge.votingsessionmanager.persistence.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CreateVotePersistenceAdapter implements CreateVoteOutputPort {
    private final VotePersistenceMapper votePersistenceMapper;
    private final VoteRepository voteRepository;

    @Override public Mono<VoteDataTransfer> create(final VoteDataTransfer voteDataTransfer) {
        return Mono.just(voteDataTransfer)
                .map(votePersistenceMapper::voteDataTransferToVoteEntity)
                .map(voteRepository::save)
                .map(votePersistenceMapper::voteEntityToVoteDataTransfer)
                .log();
    }
}

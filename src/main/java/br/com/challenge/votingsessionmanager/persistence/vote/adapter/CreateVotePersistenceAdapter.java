package br.com.challenge.votingsessionmanager.persistence.vote.adapter;

import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
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

    @Override public Mono<Vote> create(final Vote vote) {
        return Mono.just(vote)
                .map(votePersistenceMapper::voteToVoteEntity)
                .map(voteRepository::save)
                .map(votePersistenceMapper::voteEntityToVote)
                .log();
    }
}

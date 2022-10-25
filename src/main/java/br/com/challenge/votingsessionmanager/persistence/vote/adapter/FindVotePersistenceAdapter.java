package br.com.challenge.votingsessionmanager.persistence.vote.adapter;

import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import br.com.challenge.votingsessionmanager.core.votes.port.FindVoteOutputPort;
import br.com.challenge.votingsessionmanager.persistence.vote.VoteEntity;
import br.com.challenge.votingsessionmanager.persistence.vote.VotePersistenceMapper;
import br.com.challenge.votingsessionmanager.persistence.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class FindVotePersistenceAdapter implements FindVoteOutputPort {
    private final VotePersistenceMapper votePersistenceMapper;
    private final VoteRepository voteRepository;

    @Override
    public Mono<Vote> findByAffiliateId(final Integer affiliateId) {
        return Mono.just(affiliateId)
                .map(voteRepository::findByAffiliateId)
                .map(optVoteEntity -> optVoteEntity.orElse(new VoteEntity()))
                .map(votePersistenceMapper::voteEntityToVote)
                .log();
    }
}

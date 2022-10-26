package br.com.challenge.votingsessionmanager.persistence.vote.adapter;

import java.util.Optional;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.port.FindVoteOutputPort;
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

    @Override public Mono<VoteDataTransfer> findBySessionIdAffiliateId(final Integer sessionId, final Integer affiliateId) {
        return Mono.just(voteRepository.findBySessionIdAndAffiliateId(sessionId, affiliateId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(votePersistenceMapper::voteEntityToVoteDataTransfer)
                .log();
    }
}

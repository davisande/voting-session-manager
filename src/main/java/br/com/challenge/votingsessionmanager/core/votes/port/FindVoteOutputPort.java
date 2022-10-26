package br.com.challenge.votingsessionmanager.core.votes.port;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import reactor.core.publisher.Mono;

public interface FindVoteOutputPort {

    Mono<VoteDataTransfer> findBySessionIdAffiliateId(Integer sessionId, Integer affiliateId);

}

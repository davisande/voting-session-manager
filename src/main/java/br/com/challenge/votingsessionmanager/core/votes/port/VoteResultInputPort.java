package br.com.challenge.votingsessionmanager.core.votes.port;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteResultDataTransfer;
import reactor.core.publisher.Mono;

public interface VoteResultInputPort {

    Mono<VoteResultDataTransfer> calculateVotes(Integer sessionId);

}

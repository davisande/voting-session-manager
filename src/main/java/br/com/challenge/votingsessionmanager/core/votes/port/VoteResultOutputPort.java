package br.com.challenge.votingsessionmanager.core.votes.port;

import java.util.List;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteCountedDataTransfer;
import reactor.core.publisher.Mono;

public interface VoteResultOutputPort {

    Mono<List<VoteCountedDataTransfer>> getAmountVotes(Integer sessionId);

}

package br.com.challenge.votingsessionmanager.core.votes.port;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import reactor.core.publisher.Mono;

public interface CreateVoteOutputPort {

    Mono<VoteDataTransfer> create(VoteDataTransfer voteDataTransfer);

}

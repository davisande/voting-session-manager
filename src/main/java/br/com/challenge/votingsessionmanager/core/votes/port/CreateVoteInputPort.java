package br.com.challenge.votingsessionmanager.core.votes.port;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.CreateVoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import reactor.core.publisher.Mono;

public interface CreateVoteInputPort {

    Mono<VoteDataTransfer> createVote(Integer sessionId, CreateVoteDataTransfer createVoteDataTransfer);

}

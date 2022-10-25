package br.com.challenge.votingsessionmanager.core.votes.port;

import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import reactor.core.publisher.Mono;

public interface CreateVoteOutputPort {

    Mono<Vote> create(Vote vote);

}

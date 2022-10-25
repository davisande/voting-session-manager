package br.com.challenge.votingsessionmanager.api.vote.handle;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteResultDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.port.VoteResultInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class VoteResultHandle {
    private final VoteResultInputPort voteResultInputPort;

    public Mono<ServerResponse> getVoteResult(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(calculateVoteResult(serverRequest), VoteResultDataTransfer.class);
    }

    private Mono<VoteResultDataTransfer> calculateVoteResult(final ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("session_id"))
                .map(Integer::valueOf)
                .flatMap(voteResultInputPort::calculateVotes)
                .log();
    }

}

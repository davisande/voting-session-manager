package br.com.challenge.votingsessionmanager.api.vote.handle;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.CreateVoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.port.CreateVoteInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CreateVoteHandle {
    private final CreateVoteInputPort createVoteInputPort;

    public Mono<ServerResponse> createVote(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(performVoteCreation(serverRequest), VoteDataTransfer.class);
    }

    private Mono<VoteDataTransfer> performVoteCreation(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateVoteDataTransfer.class)
                .flatMap(createVoteDataTransfer ->
                                 createVoteInputPort.createVote(Integer.valueOf(serverRequest.pathVariable("session_id")),
                                                                createVoteDataTransfer)
                )
                .log();
    }

}

package br.com.challenge.votingsessionmanager.api.vote;

import br.com.challenge.votingsessionmanager.api.vote.handle.CreateVoteHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class VoteApiRoute {
    private static final String VOTE_URL_PATH = "/sessions/{session_id}/votes";

    @Bean("voteRoutes")
    public RouterFunction<ServerResponse> makeVoteRoutes(final CreateVoteHandle createVoteHandle) {
        return RouterFunctions.route(
                RequestPredicates.POST(VOTE_URL_PATH)
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON))
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                createVoteHandle::createVote);
    }
}

package br.com.challenge.votingsessionmanager.api.vote;

import br.com.challenge.votingsessionmanager.api.vote.handle.CreateVoteHandle;
import br.com.challenge.votingsessionmanager.api.vote.handle.VoteResultHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@RequiredArgsConstructor
@Configuration
public class VoteApiRoute {
    private static final String VOTE_URL_PATH = "/sessions/{session_id}/votes";
    private static final String VOTING_RESULT_URL_PATH = "/sessions/{session_id}/votes/results";

    private final CreateVoteHandle createVoteHandle;
    private final VoteResultHandle voteResultHandle;

    @Bean("voteRoutes")
    public RouterFunction<ServerResponse> makeVoteRoutes() {
        return RouterFunctions.route()
                .POST(VOTE_URL_PATH,
                      RequestPredicates.contentType(MediaType.APPLICATION_JSON)
                              .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                      createVoteHandle::createVote)
                .GET(VOTING_RESULT_URL_PATH,
                     RequestPredicates.accept(MediaType.APPLICATION_JSON),
                     voteResultHandle::getVoteResult)
                .build();
    }
}

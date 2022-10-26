package br.com.challenge.votingsessionmanager.api.vote;

import br.com.challenge.votingsessionmanager.api.session.handle.CreateSessionHandle;
import br.com.challenge.votingsessionmanager.api.vote.handle.CreateVoteHandle;
import br.com.challenge.votingsessionmanager.api.vote.handle.VoteResultHandle;
import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.CreateVoteDataTransfer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RouterOperations({
        @RouterOperation(path = VOTE_URL_PATH, produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = CreateVoteHandle.class, beanMethod = "createVote",
            operation = @Operation(operationId = "createVote", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = CreateVoteDataTransfer.class)))}
            )),
        @RouterOperation(path = VOTING_RESULT_URL_PATH, produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = VoteResultHandle.class, method = RequestMethod.GET, beanMethod = "getVoteResult",
            operation = @Operation(operationId = "getVoteResult", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation")},
                    parameters = {@Parameter(in = ParameterIn.QUERY, name = "session_id")}
            ))
    })
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

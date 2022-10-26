package br.com.challenge.votingsessionmanager.api.session;

import br.com.challenge.votingsessionmanager.api.session.handle.CreateSessionHandle;
import br.com.challenge.votingsessionmanager.core.session.datatransfer.CreateSessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@Configuration
public class SessionApiRoute {
    private static final String SESSION_URL_PATH = "/topics/{topic_id}/sessions";

    @RouterOperations(
            @RouterOperation(
                    path = SESSION_URL_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = CreateSessionHandle.class, beanMethod = "createSession",
                    operation = @Operation(
                            summary = "Create Session",
                            description = "Create Session",
                            operationId = "createSession",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "topic_id")
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(implementation = CreateSessionDataTransfer.class))
                            ),
                            responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "successful operation",
                                        content = @Content(schema = @Schema(implementation = SessionDataTransfer.class))
                                )
                            }
                    )
            )
    )
    @Bean("sessionRoutes")
    public RouterFunction<ServerResponse> makeSessionRoutes(final CreateSessionHandle createSessionHandle) {
        return RouterFunctions.route(
                RequestPredicates.POST(SESSION_URL_PATH)
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON))
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                createSessionHandle::createSession);
    }
}

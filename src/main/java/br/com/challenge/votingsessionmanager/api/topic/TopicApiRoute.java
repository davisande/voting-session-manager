package br.com.challenge.votingsessionmanager.api.topic;

import br.com.challenge.votingsessionmanager.api.topic.handle.CreateTopicHandle;
import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class TopicApiRoute {
    private static final String TOPIC_URL_PATH = "/topics";

    @RouterOperations(
        @RouterOperation(path = TOPIC_URL_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = CreateTopicHandle.class, method = RequestMethod.POST, beanMethod = "createTopic",
            operation = @Operation(operationId = "createTopic", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = TopicDataTransfer.class)))
                }
            ))
    )
    @Bean("topicRoutes")
    public RouterFunction<ServerResponse> makeTopicRoutes(final CreateTopicHandle createTopicHandle) {
        return RouterFunctions.route(
                RequestPredicates.POST(TOPIC_URL_PATH)
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON))
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                createTopicHandle::createTopic);
    }
}

package br.com.challenge.votingsessionmanager.api.topic;

import br.com.challenge.votingsessionmanager.api.topic.handle.CreateTopicHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TopicApiRoute {
    private static final String TOPIC_URL_PATH = "/topics";

    @Bean("topicRoutes")
    public RouterFunction<ServerResponse> makeTopicRoutes(final CreateTopicHandle createTopicHandle) {
        return RouterFunctions.route(
                RequestPredicates.POST(TOPIC_URL_PATH)
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON))
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                createTopicHandle::createTopic);
    }
}

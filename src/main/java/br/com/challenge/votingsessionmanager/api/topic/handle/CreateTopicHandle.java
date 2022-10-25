package br.com.challenge.votingsessionmanager.api.topic.handle;

import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.port.CreateTopicInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CreateTopicHandle {
    private final CreateTopicInputPort createTopicInputPort;

    public Mono<ServerResponse> createTopic(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(performTopicCreation(serverRequest), TopicDataTransfer.class);
    }

    private Mono<TopicDataTransfer> performTopicCreation(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(TopicDataTransfer.class)
                .flatMap(createTopicInputPort::createTopic)
                .log();
    }
}

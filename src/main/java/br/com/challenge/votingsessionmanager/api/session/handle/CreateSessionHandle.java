package br.com.challenge.votingsessionmanager.api.session.handle;

import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.port.CreateSessionInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CreateSessionHandle {
    private final CreateSessionInputPort createSessionInputPort;

    public Mono<ServerResponse> createSession(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(performSessionCreation(serverRequest), SessionDataTransfer.class);
    }

    private Mono<SessionDataTransfer> performSessionCreation(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SessionDataTransfer.class)
                .map(sessionDataTransfer -> setSessionTopicId(sessionDataTransfer, serverRequest))
                .flatMap(createSessionInputPort::createSession)
                .log();
    }

    private SessionDataTransfer setSessionTopicId(final SessionDataTransfer sessionDataTransfer, final ServerRequest serverRequest) {
        final Integer topicId = Integer.valueOf(serverRequest.pathVariable("topic_id"));
        sessionDataTransfer.setTopicId(topicId);

        return sessionDataTransfer;
    }
}

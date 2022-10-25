package br.com.challenge.votingsessionmanager.core.session.port;

import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;
import reactor.core.publisher.Mono;

public interface CreateSessionInputPort {

    Mono<SessionDataTransfer> createSession(SessionDataTransfer sessionDataTransfer);

}

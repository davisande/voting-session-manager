package br.com.challenge.votingsessionmanager.core.session.port;

import br.com.challenge.votingsessionmanager.core.session.domain.Session;
import br.com.challenge.votingsessionmanager.core.topic.domain.Topic;
import reactor.core.publisher.Mono;

public interface CreateSessionOutputPort {

    Mono<Session> create(Session topic);

}

package br.com.challenge.votingsessionmanager.core.session.port;

import br.com.challenge.votingsessionmanager.core.session.domain.Session;
import reactor.core.publisher.Mono;

public interface CreateSessionOutputPort {

    Mono<Session> create(Session topic);

}

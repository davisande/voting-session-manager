package br.com.challenge.votingsessionmanager.core.session.port;

import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import reactor.core.publisher.Mono;

public interface FindSessionOutputPort {

    Mono<SessionDataTransfer> findById(Integer sessionId);

}

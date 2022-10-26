package br.com.challenge.votingsessionmanager.core.session.port;

import br.com.challenge.votingsessionmanager.core.session.datatransfer.CreateSessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import reactor.core.publisher.Mono;

public interface CreateSessionInputPort {

    Mono<SessionDataTransfer> createSession(Integer topicId, CreateSessionDataTransfer createSessionDataTransfer);

}

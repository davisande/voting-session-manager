package br.com.challenge.votingsessionmanager.persistence.session.adapter;

import java.util.Optional;

import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.port.FindSessionOutputPort;
import br.com.challenge.votingsessionmanager.persistence.session.SessionPersistenceMapper;
import br.com.challenge.votingsessionmanager.persistence.session.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class FindSessionPersistenceAdapter implements FindSessionOutputPort {
    private final SessionPersistenceMapper sessionPersistenceMapper;
    private final SessionRepository sessionRepository;

    @Override public Mono<SessionDataTransfer> findById(final Integer sessionId) {
        return Mono.just(sessionId)
                .map(sessionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(sessionPersistenceMapper::sessionEntityToSessionDataTransfer)
                .log();
    }
}

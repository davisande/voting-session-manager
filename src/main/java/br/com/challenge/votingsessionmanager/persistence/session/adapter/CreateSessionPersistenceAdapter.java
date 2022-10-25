package br.com.challenge.votingsessionmanager.persistence.session.adapter;

import br.com.challenge.votingsessionmanager.core.session.domain.Session;
import br.com.challenge.votingsessionmanager.core.session.port.CreateSessionOutputPort;
import br.com.challenge.votingsessionmanager.persistence.session.SessionPersistenceMapper;
import br.com.challenge.votingsessionmanager.persistence.session.SessionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CreateSessionPersistenceAdapter implements CreateSessionOutputPort {
    private final SessionPersistenceMapper sessionPersistenceMapper;
    private final SessionRepository sessionRepository;

    @Override
    public Mono<Session> create(@NonNull final Session session) {
        return Mono.just(session)
                .map(sessionPersistenceMapper::sessionToSessionEntity)
                .map(sessionRepository::save)
                .map(sessionPersistenceMapper::sessionEntityToSession)
                .log();
    }
}

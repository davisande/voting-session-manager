package br.com.challenge.votingsessionmanager.core.session.usecase;

import br.com.challenge.votingsessionmanager.core.exception.UnexpectedErrorException;
import br.com.challenge.votingsessionmanager.core.session.datatransfer.CreateSessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.domain.Session;
import br.com.challenge.votingsessionmanager.core.session.mapper.SessionMapper;
import br.com.challenge.votingsessionmanager.core.session.port.CreateSessionInputPort;
import br.com.challenge.votingsessionmanager.core.session.port.CreateSessionOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateSessionUseCase implements CreateSessionInputPort {
    private final SessionMapper sessionMapper;
    private final CreateSessionOutputPort createSessionOutputPort;

    @Override
    public Mono<SessionDataTransfer> createSession(@NonNull final Integer topicId,
                                                   @NonNull final CreateSessionDataTransfer createSessionDataTransfer) {
        log.info("Creating session: [topicId: %s, createSessionDataTransfer: %s]", topicId, createSessionDataTransfer);

        return Mono.just(createSessionDataTransfer)
                .map(dt -> sessionMapper.createSessionDataTransferToSession(topicId, dt))
                .map(Session::defineSessionOpenTime)
                .flatMap(createSessionOutputPort::create)
                .map(sessionMapper::sessionToSessionDataTransfer)
                .log()
                .onErrorMap(e -> new UnexpectedErrorException("Unexpected error when create topic", e));
    }

}

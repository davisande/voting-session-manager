package br.com.challenge.votingsessionmanager.core.topic.usecase;

import br.com.challenge.votingsessionmanager.core.exception.UnexpectedErrorException;
import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.mapper.TopicMapper;
import br.com.challenge.votingsessionmanager.core.topic.port.CreateTopicInputPort;
import br.com.challenge.votingsessionmanager.core.topic.port.CreateTopicOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateTopicUseCase implements CreateTopicInputPort {
    private final TopicMapper topicMapper;
    private final CreateTopicOutputPort createTopicOutputPort;

    public Mono<TopicDataTransfer> createTopic(@NonNull final TopicDataTransfer topicDataTransfer) {
        log.info("Creating topic: " + topicDataTransfer);

        return Mono.just(topicDataTransfer)
                .map(topicMapper::topicDataTransferToTopic)
                .flatMap(createTopicOutputPort::create)
                .map(topicMapper::topicToTopicDataTransfer)
                .log()
                .onErrorMap(e -> new UnexpectedErrorException("Unexpected error when create topic", e));
    }

}

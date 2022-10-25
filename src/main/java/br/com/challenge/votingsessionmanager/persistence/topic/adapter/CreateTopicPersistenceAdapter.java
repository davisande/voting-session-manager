package br.com.challenge.votingsessionmanager.persistence.topic.adapter;

import java.util.Objects;

import br.com.challenge.votingsessionmanager.core.topic.domain.Topic;
import br.com.challenge.votingsessionmanager.core.topic.port.CreateTopicOutputPort;
import br.com.challenge.votingsessionmanager.persistence.topic.TopicPersistenceMapper;
import br.com.challenge.votingsessionmanager.persistence.topic.TopicRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CreateTopicPersistenceAdapter implements CreateTopicOutputPort {
    private final TopicPersistenceMapper topicPersistenceMapper;
    private final TopicRepository topicRepository;

    @Override
    public Mono<Topic> create(@NonNull final Topic topic) {
        return Mono.just(topic)
                .map(topicPersistenceMapper::topicToTopicEntity)
                .map(topicRepository::save)
                .map(topicPersistenceMapper::topicEntityToTopic)
                .log();
    }
}

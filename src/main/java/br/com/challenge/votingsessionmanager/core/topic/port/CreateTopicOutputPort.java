package br.com.challenge.votingsessionmanager.core.topic.port;

import br.com.challenge.votingsessionmanager.core.topic.domain.Topic;
import reactor.core.publisher.Mono;

public interface CreateTopicOutputPort {

    Mono<Topic> create(Topic topic);

}

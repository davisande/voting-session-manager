package br.com.challenge.votingsessionmanager.core.topic.port;

import br.com.challenge.votingsessionmanager.core.topic.datatransfer.CreateTopicDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;
import reactor.core.publisher.Mono;

public interface CreateTopicInputPort {

    Mono<TopicDataTransfer> createTopic(CreateTopicDataTransfer createTopicDataTransfer);

}

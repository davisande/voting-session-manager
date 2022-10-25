package br.com.challenge.votingsessionmanager.core.topic.mapper;

import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.domain.Topic;
import br.com.challenge.votingsessionmanager.persistence.topic.TopicEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    Topic topicDataTransferToTopic(TopicDataTransfer topicDataTransfer);

    TopicDataTransfer topicToTopicDataTransfer(Topic topic);

}

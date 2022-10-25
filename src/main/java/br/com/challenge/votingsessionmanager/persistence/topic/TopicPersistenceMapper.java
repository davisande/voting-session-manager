package br.com.challenge.votingsessionmanager.persistence.topic;

import br.com.challenge.votingsessionmanager.core.topic.domain.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicPersistenceMapper {

    TopicEntity topicToTopicEntity(Topic topic);

    Topic topicEntityToTopic(TopicEntity topicEntity);

}

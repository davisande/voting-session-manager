package br.com.challenge.votingsessionmanager.core.topic.mapper;

import br.com.challenge.votingsessionmanager.core.topic.datatransfer.CreateTopicDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.domain.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TopicMapper {

    Topic createTopicDataTransferToTopic(CreateTopicDataTransfer createTopicDataTransfer);

    TopicDataTransfer topicToTopicDataTransfer(Topic topic);

}

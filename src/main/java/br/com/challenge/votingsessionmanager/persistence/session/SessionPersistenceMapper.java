package br.com.challenge.votingsessionmanager.persistence.session;

import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.domain.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessionPersistenceMapper {

    @Mapping(target = "topic.topicId", source = "topicId")
    SessionEntity sessionToSessionEntity(Session session);

    @Mapping(target = "topicId", source = "topic.topicId")
    Session sessionEntityToSession(SessionEntity sessionEntity);

    @Mapping(target = "topicId", source = "topic.topicId")
    SessionDataTransfer sessionEntityToSessionDataTransfer(SessionEntity sessionEntity);

}

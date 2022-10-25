package br.com.challenge.votingsessionmanager.core.session.mapper;

import br.com.challenge.votingsessionmanager.core.session.datatransfer.SessionDataTransfer;
import br.com.challenge.votingsessionmanager.core.session.domain.Session;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessionMapper {

    Session sessionDataTransferToSession(SessionDataTransfer sessionDataTransfer);

    SessionDataTransfer sessionToSessionDataTransfer(Session session);

}

package br.com.challenge.votingsessionmanager.core.session.datatransfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSessionDataTransfer {
    private String description;
    private Integer votingTime;

}

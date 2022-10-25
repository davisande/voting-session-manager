package br.com.challenge.votingsessionmanager.core.session.datatransfer;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDataTransfer {
    private Integer sessionId;
    private Integer topicId;
    private String description;
    private Integer votingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

package br.com.challenge.votingsessionmanager.core.topic.datatransfer;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicDataTransfer {
    private Integer topicId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

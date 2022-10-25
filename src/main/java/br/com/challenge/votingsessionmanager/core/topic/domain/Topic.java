package br.com.challenge.votingsessionmanager.core.topic.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Topic {
    private Integer topicId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

package br.com.challenge.votingsessionmanager.mock;

import java.time.LocalDateTime;

import br.com.challenge.votingsessionmanager.core.topic.domain.Topic;

public class TopicMockBuilder {
    private static final Integer TOPIC_ID = 1;

    public static Topic buildCreationMock(final String description) {
        return Topic.builder()
                .description(description)
                .build();
    }

    public static Topic buildCompleteMock(final String description, final LocalDateTime currentDate) {
        return Topic.builder()
                .topicId(TOPIC_ID)
                .description(description)
                .createdAt(currentDate)
                .updatedAt(currentDate)
                .build();
    }

}

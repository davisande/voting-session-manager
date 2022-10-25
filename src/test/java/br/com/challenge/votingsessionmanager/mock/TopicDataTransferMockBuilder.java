package br.com.challenge.votingsessionmanager.mock;

import java.time.LocalDateTime;

import br.com.challenge.votingsessionmanager.core.topic.datatransfer.TopicDataTransfer;

public class TopicDataTransferMockBuilder {
    private static final Integer TOPIC_ID = 1;
    public static final String TOPIC_DESCRIPTION = "Description test";

    public static TopicDataTransfer buildCreationMock(final String description) {
        return TopicDataTransfer.builder()
                .description(description)
                .build();
    }

    public static TopicDataTransfer buildCompleteMock(final String description, final LocalDateTime currentDate) {
        return TopicDataTransfer.builder()
                .topicId(TOPIC_ID)
                .description(description)
                .createdAt(currentDate)
                .updatedAt(currentDate)
                .build();
    }

}

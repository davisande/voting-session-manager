package br.com.challenge.votingsessionmanager.core.topic;

import static br.com.challenge.votingsessionmanager.mock.TopicDataTransferMockBuilder.TOPIC_DESCRIPTION;

import java.time.LocalDateTime;

import br.com.challenge.votingsessionmanager.core.topic.datatransfer.CreateTopicDataTransfer;
import br.com.challenge.votingsessionmanager.core.topic.mapper.TopicMapper;
import br.com.challenge.votingsessionmanager.core.topic.port.CreateTopicOutputPort;
import br.com.challenge.votingsessionmanager.core.topic.usecase.CreateTopicUseCase;
import br.com.challenge.votingsessionmanager.mock.TopicDataTransferMockBuilder;
import br.com.challenge.votingsessionmanager.mock.TopicMockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class CreateTopicUseCaseTest {
    private static final String NULL_POINTER_MESSAGE = "createTopicDataTransfer is marked non-null but is null";
    private static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected error when create topic";

    @Mock
    private TopicMapper topicMapper;

    @Mock
    private CreateTopicOutputPort createTopicOutputPort;

    @InjectMocks
    private CreateTopicUseCase createTopicUseCase;

    @Test
    void testCreateTopicWhenHasMandatoryCreationDataExpectTopicSaved() {
        final var creationTopic = TopicMockBuilder.buildCreationMock(TOPIC_DESCRIPTION);

        final var currentDate = LocalDateTime.now();
        final var createdTopic = TopicMockBuilder.buildCompleteMock(TOPIC_DESCRIPTION, currentDate);

        final var createdTopicDataTransfer = TopicDataTransferMockBuilder.buildCompleteMock(TOPIC_DESCRIPTION, currentDate);
        final var creationTopicDataTransfer = new CreateTopicDataTransfer();
        creationTopicDataTransfer.setDescription(TOPIC_DESCRIPTION);

        Mockito.when(topicMapper.createTopicDataTransferToTopic(Mockito.any())).thenReturn(creationTopic);
        Mockito.when(createTopicOutputPort.create(Mockito.any())).thenReturn(Mono.just(createdTopic));
        Mockito.when(topicMapper.topicToTopicDataTransfer(Mockito.any())).thenReturn(createdTopicDataTransfer);

        final var monoTopicDataTransfer = createTopicUseCase.createTopic(creationTopicDataTransfer);

        Assertions.assertThat(monoTopicDataTransfer).isNotNull();

        StepVerifier.create(monoTopicDataTransfer)
                .expectNext(createdTopicDataTransfer)
                .verifyComplete();
    }

    @Test
    void testCreateTopicWhenCreationObjectIsNullExpectedNullPointerError() {
        var nullPointerException = Assertions.catchNullPointerException(() -> createTopicUseCase.createTopic(null));

        Assertions.assertThat(nullPointerException).hasMessageContaining(NULL_POINTER_MESSAGE);
    }

    @Test
    void testCreateTopicWhenDatabaseErrorHappensExpectedUnexpectedError() {
        final var creationTopic = TopicMockBuilder.buildCreationMock(TOPIC_DESCRIPTION);
        final var creationTopicDataTransfer = new CreateTopicDataTransfer();
        creationTopicDataTransfer.setDescription(TOPIC_DESCRIPTION);

        Mockito.lenient().when(topicMapper.createTopicDataTransferToTopic(Mockito.any())).thenReturn(creationTopic);
        Mockito.lenient().when(createTopicOutputPort.create(Mockito.any())).thenThrow(new IllegalArgumentException());

        StepVerifier.create(createTopicUseCase.createTopic(creationTopicDataTransfer)).expectErrorMessage(UNEXPECTED_ERROR_MESSAGE);
    }

}

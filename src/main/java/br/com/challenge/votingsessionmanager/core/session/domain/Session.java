package br.com.challenge.votingsessionmanager.core.session.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Session {
    private static final Integer ONE_MINUTE = 1;

    private Integer sessionId;
    private Integer topicId;
    private String description;
    private Integer votingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session defineSessionOpenTime() {
        if (Objects.isNull(votingTime)) {
            votingTime = ONE_MINUTE;
        }

        startDate = LocalDateTime.now();
        endDate = startDate.plusMinutes(votingTime);

        return this;
    }

    public boolean isSessionOpen() {
        final var currentDate = LocalDateTime.now();
        final var isEqualsAfterStartDate = currentDate.equals(startDate) || currentDate.isAfter(startDate);
        final var isEqualsBeforeEndDate = currentDate.equals(endDate) || currentDate.isBefore(endDate);

        return isEqualsAfterStartDate && isEqualsBeforeEndDate;
    }
}

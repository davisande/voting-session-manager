package br.com.challenge.votingsessionmanager.core.votes.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.challenge.votingsessionmanager.core.votes.enumerator.OptionVoteEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vote {
    private Integer voteId;
    private Integer sessionId;
    private Integer affiliateId;
    private OptionVoteEnum option;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean isValidVote() {
        return Objects.isNull(voteId) && Objects.isNull(sessionId) && Objects.isNull(affiliateId);
    }
}

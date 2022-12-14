package br.com.challenge.votingsessionmanager.core.votes.datatransfer;

import java.time.LocalDateTime;

import br.com.challenge.votingsessionmanager.core.votes.enumerator.OptionVoteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteDataTransfer {
    private Integer voteId;
    private Integer sessionId;
    private Integer affiliateId;
    private OptionVoteEnum option;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

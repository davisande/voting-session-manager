package br.com.challenge.votingsessionmanager.core.votes.datatransfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteResultDataTransfer {
    private Integer sessionId;
    private Integer amountVotesFavor;
    private Integer amountVotesAgainst;

}

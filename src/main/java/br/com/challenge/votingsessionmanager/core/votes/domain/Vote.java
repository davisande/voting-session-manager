package br.com.challenge.votingsessionmanager.core.votes.domain;

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

}

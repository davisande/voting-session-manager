package br.com.challenge.votingsessionmanager.core.votes.datatransfer;

import br.com.challenge.votingsessionmanager.core.votes.enumerator.OptionVoteEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateVoteDataTransfer {
    private Integer affiliateId;
    private OptionVoteEnum option;

}

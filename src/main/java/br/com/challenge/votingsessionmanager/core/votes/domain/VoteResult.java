package br.com.challenge.votingsessionmanager.core.votes.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.challenge.votingsessionmanager.core.exception.IllegalParameterException;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteCountedDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.enumerator.OptionVoteEnum;
import br.com.challenge.votingsessionmanager.persistence.vote.entity.VoteResultEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteResult {
    private Integer sessionId;
    private OptionVoteEnum option;
    private Integer amountVotesFavor;
    private Integer amountVotesAgainst;
    private List<VoteCountedDataTransfer> votesCounted;

    public void calculateVoteResult() {
        if (Objects.isNull(votesCounted)) {
            throw new IllegalParameterException("Amount votes is mandatory");
        }

        final var voteResultByVoteOption = votesCounted.stream()
                .collect(Collectors.groupingBy(v -> v.getOption()));

        voteResultByVoteOption.forEach((key, votesResult) -> {
            final var voteResultEntity = votesResult.get(0);
            this.sessionId = voteResultEntity.getSessionId();
            if (OptionVoteEnum.YES.equals(key)) {
                this.amountVotesFavor = voteResultEntity.getAmountVotes();
            } else if (OptionVoteEnum.NO.equals(key)) {
                this.amountVotesAgainst = voteResultEntity.getAmountVotes();
            }
        });
    }
}

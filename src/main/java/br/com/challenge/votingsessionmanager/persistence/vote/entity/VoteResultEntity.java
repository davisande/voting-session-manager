package br.com.challenge.votingsessionmanager.persistence.vote.entity;

import br.com.challenge.votingsessionmanager.core.votes.enumerator.OptionVoteEnum;

public interface VoteResultEntity {

    Integer getSessionId();
    OptionVoteEnum getOption();
    Integer getAmountVotes();

}

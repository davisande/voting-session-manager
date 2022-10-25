package br.com.challenge.votingsessionmanager.core.votes.enumerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum OptionVoteEnum {
    SIM("Sim"), NAO("Não");

    private final String description;
}

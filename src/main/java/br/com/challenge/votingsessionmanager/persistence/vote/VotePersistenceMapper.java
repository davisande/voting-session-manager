package br.com.challenge.votingsessionmanager.persistence.vote;

import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotePersistenceMapper {

    @Mapping(target = "session.sessionId", source = "sessionId")
    VoteEntity voteToVoteEntity(Vote vote);

    @Mapping(target = "sessionId", source = "session.sessionId")
    Vote voteEntityToVote(VoteEntity voteEntity);

}

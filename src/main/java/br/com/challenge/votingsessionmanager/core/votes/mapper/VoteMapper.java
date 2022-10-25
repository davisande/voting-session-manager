package br.com.challenge.votingsessionmanager.core.votes.mapper;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    Vote voteDataTransferToVote(VoteDataTransfer voteDataTransfer);

    VoteDataTransfer voteToVoteDataTransfer(Vote vote);

}

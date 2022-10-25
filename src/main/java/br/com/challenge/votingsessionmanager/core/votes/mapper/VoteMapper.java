package br.com.challenge.votingsessionmanager.core.votes.mapper;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteResultDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.domain.Vote;
import br.com.challenge.votingsessionmanager.core.votes.domain.VoteResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface VoteMapper {

    Vote voteDataTransferToVote(VoteDataTransfer voteDataTransfer);

    VoteDataTransfer voteToVoteDataTransfer(Vote vote);

    VoteResultDataTransfer voteResultToVoteResultDataTransfer(VoteResult voteResult);

}

package br.com.challenge.votingsessionmanager.persistence.vote;

import java.util.List;
import java.util.stream.Collectors;

import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteCountedDataTransfer;
import br.com.challenge.votingsessionmanager.core.votes.datatransfer.VoteDataTransfer;
import br.com.challenge.votingsessionmanager.persistence.vote.entity.VoteResultEntity;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VotePersistenceMapper {

    @Mapping(target = "session.sessionId", source = "sessionId")
    VoteEntity voteDataTransferToVoteEntity(VoteDataTransfer vote);

    @Mapping(target = "sessionId", source = "session.sessionId")
    VoteDataTransfer voteEntityToVoteDataTransfer(VoteEntity voteEntity);

    default List<VoteCountedDataTransfer> voteResultEntityToVoteCountedDataTransfer(@NonNull List<VoteResultEntity> voteResults) {
        return voteResults.stream()
                .map(voteResultEntity -> VoteCountedDataTransfer.builder()
                        .sessionId(voteResultEntity.getSessionId())
                        .option(voteResultEntity.getOption())
                        .amountVotes(voteResultEntity.getAmountVotes())
                        .build())
                .collect(Collectors.toList());
    }

}

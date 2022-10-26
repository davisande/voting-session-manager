package br.com.challenge.votingsessionmanager.persistence.vote;

import java.util.List;
import java.util.Optional;

import br.com.challenge.votingsessionmanager.persistence.vote.entity.VoteResultEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Integer> {

    @Query("SELECT v FROM VoteEntity v WHERE v.session.sessionId = :sessionId and v.affiliateId = :affiliateId")
    Optional<VoteEntity> findBySessionIdAndAffiliateId(@Param("sessionId") Integer sessionId,
                                                       @Param("affiliateId") Integer affiliateId);

    @Query("SELECT v.session.sessionId AS sessionId, v.option AS option, COUNT(v.option) AS amountVotes " +
            "FROM VoteEntity AS v " +
            "WHERE v.session.sessionId = :sessionId " +
            "GROUP BY v.session.sessionId, v.option")
    List<VoteResultEntity> countVotesBySession(@Param("sessionId") Integer sessionId);

}

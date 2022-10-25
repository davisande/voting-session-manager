package br.com.challenge.votingsessionmanager.persistence.vote;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Integer> {

    Optional<VoteEntity> findByAffiliateId(Integer affiliateId);

}

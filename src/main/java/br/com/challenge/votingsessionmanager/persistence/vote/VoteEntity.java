package br.com.challenge.votingsessionmanager.persistence.vote;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.challenge.votingsessionmanager.core.votes.enumerator.OptionVoteEnum;
import br.com.challenge.votingsessionmanager.persistence.session.SessionEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity()
@Table(name = "votes")
@EntityListeners(AuditingEntityListener.class)
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Integer voteId;

    @OneToOne
    @JoinColumn(name = "session_id")
    private SessionEntity session;

    @Column(name = "affiliate_id")
    private Integer affiliateId;

    @Enumerated(EnumType.STRING)
    private OptionVoteEnum option;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

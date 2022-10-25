package br.com.challenge.votingsessionmanager.persistence.session;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.challenge.votingsessionmanager.persistence.topic.TopicEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity()
@Table(name = "sessions")
@EntityListeners(AuditingEntityListener.class)
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Integer sessionId;

    @ManyToOne()
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @Column()
    private String description;

    @Column(name = "start_date_time")
    private LocalDateTime startDate;

    @Column(name = "end_date_time")
    private LocalDateTime endDate;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

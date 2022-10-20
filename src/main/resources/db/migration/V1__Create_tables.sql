CREATE TABLE IF NOT EXISTS topics (
    topic_id serial NOT NULL,
    description VARCHAR(150) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT topics_pk PRIMARY KEY (topic_id)
);

CREATE TABLE IF NOT EXISTS sessions (
    session_id serial NOT NULL,
    topic_id INT NOT NULL,
    description VARCHAR(150) NOT NULL,
    start_date_time TIMESTAMP NOT NULL,
    end_date_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT sessions_pk PRIMARY KEY (session_id),
    CONSTRAINT sessions_topics_fk FOREIGN KEY (topic_id) REFERENCES topics(topic_id)
);

CREATE TABLE IF NOT EXISTS votes (
    vote_id serial NOT NULL,
    session_id INT NOT NULL,
    affiliate_id INT NOT NULL UNIQUE,
    option VARCHAR(15) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT votes_pk PRIMARY KEY (session_id),
    CONSTRAINT votes_sessions_fk FOREIGN KEY (session_id) REFERENCES sessions(session_id)
);

CREATE TABLE task (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      title VARCHAR(255),
                      status VARCHAR(255),
                      created_at DATETIME(6),
                      updated_at DATETIME(6),
                      PRIMARY KEY (id)
);
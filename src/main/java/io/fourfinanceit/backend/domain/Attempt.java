package io.fourfinanceit.backend.domain;

import javax.persistence.*;

@Entity
@Table(name = "attempts")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ip;

    private Long attemptCount;

    private Long userId;

    public String getIp() {
        return ip;
    }

    public Attempt setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Long getAttemptCount() {
        return attemptCount;
    }

    public Attempt setAttemptCount(Long attemptCount) {
        this.attemptCount = attemptCount;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Attempt setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Attempt setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}

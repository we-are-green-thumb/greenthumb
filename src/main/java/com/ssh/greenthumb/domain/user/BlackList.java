package com.ssh.greenthumb.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BlackList {

    @Id
    @Column(name = "black_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blackId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "black_reason", columnDefinition = "varchar(900)")
    @NotNull
    private String blackReason;

    @Column(name = "black_status")
    @NotNull
    private String blackStatus = "y";

    @Builder
    public BlackList(User user, String blackReason) {
        this.user = user;
        this.blackReason = blackReason;
    }

    public BlackList update(String blackReason) {
        this.blackReason = blackReason;
        return this;
    }

    public void delete() {
        this.blackStatus = "n";
    }

}
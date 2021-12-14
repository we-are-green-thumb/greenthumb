package com.ssh.greenthumb.domain.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssh.greenthumb.common.domain.BaseTimeEntity;
import com.ssh.greenthumb.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    @Column(name = "post_title", columnDefinition = "varchar(300)")
    @NotNull
    private String title;

    @Column(name = "post_content", columnDefinition = "varchar(1500)")
    @NotNull
    private String content;

    @Column(name = "post_category")
    @NotNull
    private String category;

    @Column(name = "post_delete")
    @NotNull
    private String isDeleted = "n";

    @Column(name = "post_hits")
    @NotNull
    private Long hits = 0L;

    // 자유게시판을 제외한 질문, 거래 게시판 완료 여부 체크
    @Column(name = "post_check")
    private String isComplete = "n";

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<File> fileList = new ArrayList<>();

    @Builder
    public Post(User user, String title, String content, String category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Post update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;

        return this;
    }

    public void updateCheck(String isComplete) {
        if (isComplete.equals("n")) {
            this.isComplete = "y";
        } else if (isComplete.equals("y")) {
            this.isComplete = "n";
        }
    }

    public void delete() {
        this.isDeleted = "y";
    }
  
}
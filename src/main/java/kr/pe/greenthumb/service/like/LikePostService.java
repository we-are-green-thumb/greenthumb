package kr.pe.greenthumb.service.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//@Builder
public class LikePostService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likePostIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_idx")
    @NonNull
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_Idx")
    @NonNull
    private User user;
}
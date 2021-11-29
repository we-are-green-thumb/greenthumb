package kr.pe.greenthumb.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "comment_idx")
    private Long commentIdx;

    @JoinColumn(name = "board_idx")
    @NonNull
    private Long boardIdx;

    @JoinColumn(name = "user_idx")
    @NonNull
    private Long userIdx;

    @JoinColumn(name = "comment_content")
    @NonNull
    private String commentContent;

    @JoinColumn(name = "comment_date")
    @NonNull
    private LocalDate commentDate;

    @JoinColumn(name = "comment_delete")
    @NonNull
    private String commentDelete;
}
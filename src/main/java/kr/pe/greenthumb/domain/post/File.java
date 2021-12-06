package kr.pe.greenthumb.domain.post;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class File {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @Column(name = "file_url")
    @NotNull
    private String fileUrl;

}

package kr.pe.greenthumb.controller.post;

import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.dto.post.PostDTO;
import kr.pe.greenthumb.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    PostController() { System.out.println("PostController(){}"); }

    @Autowired
    private PostService postService;

    @PostMapping("/post/{postIdx}")
    public Post add(@RequestBody PostDTO.Create dto) {
        return postService.add(dto);
    }

    @GetMapping
    public List<Post> getAll(String postCategory) {
        return postService.getAll(postCategory);
    }

    @PutMapping
    public void update(Post post) {
        postService.update(post);
    }

    @DeleteMapping
    public void delete(Long postIdx) {
        postService.delete(postIdx);
    }
}

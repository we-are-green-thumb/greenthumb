package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.like.LikeCommentRepository;
import com.ssh.greenthumb.api.dao.like.LikePostRepository;
import com.ssh.greenthumb.api.dao.post.CommentRepository;
import com.ssh.greenthumb.api.dao.post.PostRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.like.LikeComment;
import com.ssh.greenthumb.api.domain.like.LikePost;
import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.like.LikeCommentDTO;
import com.ssh.greenthumb.api.dto.like.LikePostDTO;
import com.ssh.greenthumb.api.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikePostRepository likePostDao;
    private final LikeCommentRepository likeCommentDao;
    private final PostRepository postDao;
    private final CommentRepository commentDao;
    private final UserRepository userDao;

    // 게시글 좋아요 등록
    public Long likePost(Long postId, Long userId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikePostDTO.Create likePost = new LikePostDTO.Create(postId, userId);

        return likePostDao.save(likePost.toEntity(post, user)).getId();
    }

    // 게시글 좋아요 취소
    public void unLikePost(Long postId, Long userId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikePost likePost = likePostDao.findByPost(post);

        likePostDao.delete(likePost);
    }

    // 댓글 좋아요 등록
    public Long likeComment(Long commentId, Long userId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikeCommentDTO.Create dto = new LikeCommentDTO.Create(commentId, userId);

        return likeCommentDao.save(dto.toEntity(comment, user)).getId();
    }

    // 댓글 좋아요 취소
    public void unLikeComment(Long commentId, Long userId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikeComment likeComment = likeCommentDao.findByCommentAndUser(comment, user);

        likeCommentDao.delete(likeComment);
    }

}
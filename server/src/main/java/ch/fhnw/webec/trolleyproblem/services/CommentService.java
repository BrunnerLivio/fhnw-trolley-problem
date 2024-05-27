package ch.fhnw.webec.trolleyproblem.services;

import ch.fhnw.webec.trolleyproblem.components.UserSession;
import ch.fhnw.webec.trolleyproblem.dtos.CommentDto;
import ch.fhnw.webec.trolleyproblem.mappers.CommentMapper;
import ch.fhnw.webec.trolleyproblem.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserSession userSession;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserSession userSession) {
        this.commentRepository = commentRepository;
        this.userSession = userSession;
    }

    public List<CommentDto> findAll() {
        var comments = commentRepository.findAll();
        var createdComments = userSession.getCreatedComments();

        return CommentMapper.INSTANCE.commentEntityToCommentDtoList(comments)
            .stream()
            .peek(comment -> {
                if (createdComments.contains(comment.getId())) {
                    comment.setCreatedByCurrentUser(true);
                }
            })
            .toList();
    }

    public List<CommentDto> findByProblemId(Long problemId) {
        var comments = commentRepository.findAllByProblemIdOrderByCreatedAtDesc(problemId);
        var createdComments = userSession.getCreatedComments();

        return CommentMapper.INSTANCE.commentEntityToCommentDtoList(comments)
            .stream()
            .peek(comment -> {
                if (createdComments.contains(comment.getId())) {
                    comment.setCreatedByCurrentUser(true);
                }
            })
            .toList();
    }

    public CommentDto createComment(CommentDto commentDto, Long problemId) {
        var commentToSave = CommentMapper.INSTANCE.commentDtoToCommentEntity(commentDto);
        commentToSave.setProblemId(problemId);
        var newComment = commentRepository.save(commentToSave);
        this.userSession.addCreatedComment(newComment.getId());
        return CommentMapper.INSTANCE.commentEntityToCommentDto(newComment);
    }

    public CommentDto updateComment(CommentDto commentDto) {
        var comment = commentRepository.findById(commentDto.getId()).orElseThrow();
        comment.setText(commentDto.getText());
        var updatedComment = commentRepository.save(comment);
        return CommentMapper.INSTANCE.commentEntityToCommentDto(updatedComment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

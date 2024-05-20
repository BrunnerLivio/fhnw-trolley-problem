package ch.fhnw.webec.trolleyproblem.services;

import ch.fhnw.webec.trolleyproblem.dtos.CommentDto;
import ch.fhnw.webec.trolleyproblem.mappers.CommentMapper;
import ch.fhnw.webec.trolleyproblem.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository  = commentRepository;
    }

    public List<CommentDto> findByProblemId(Long problemId) {
        return CommentMapper.INSTANCE.commentEntityToCommentDtoList(commentRepository.findAllByProblemId(problemId));
    }
}

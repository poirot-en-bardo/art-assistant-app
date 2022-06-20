package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.CommentRequestDTO;
import ie.licenta.artassistant.dto.CommentResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.CommentEntity;
import ie.licenta.artassistant.persistence.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements  CommentService {

    private final CommentRepository commentRepository;
    private final ArtMapper artMapper;

    @Override
    public List<CommentResponseDTO> getAllComments() {
        return null;
    }

    @Override
    public CommentResponseDTO getCommentById(int id) {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_04_COMMENT_NOT_FOUND));
        return artMapper.commentEntityToCommentResponseDTO(comment);
    }

    @Override
    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO) {
        return artMapper.commentEntityToCommentResponseDTO(commentRepository.save(
                artMapper.commentRequestDTOToCommentEntity(commentRequestDTO)
        ));
    }

    @Override
    public CommentResponseDTO updateComment(int id, CommentRequestDTO CommentRequestDTO) {
        return null;
    }

    @Override
    public void deleteCommentById(int id) {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_04_COMMENT_NOT_FOUND));
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentResponseDTO> getAllCommentsByArtwork(int artworkId) {
        Optional<List<CommentEntity>> commentListOptional = Optional.ofNullable(
                commentRepository.findAllByArtworkIdOrderByCreatedAtDesc(artworkId));
        if (commentListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_04_COMMENT_NOT_FOUND);
        }
        return artMapper.commentEntityListToCommentResponseDTOList(commentListOptional.get());
    }
}

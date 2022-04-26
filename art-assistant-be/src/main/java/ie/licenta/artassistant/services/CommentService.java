package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.CommentRequestDTO;
import ie.licenta.artassistant.dto.CommentResponseDTO;

import java.util.List;

public interface CommentService
{
    List<CommentResponseDTO> getAllComments();

    CommentResponseDTO getCommentById(int id);

    CommentResponseDTO addComment(CommentRequestDTO CommentRequestDTO);

    CommentResponseDTO updateComment(int id, CommentRequestDTO CommentRequestDTO);

    void deleteCommentById(int id);

    List<CommentResponseDTO> getAllCommentsByArtwork(int artworkId);
}

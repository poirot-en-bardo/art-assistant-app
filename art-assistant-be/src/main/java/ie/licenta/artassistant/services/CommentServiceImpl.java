package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.CommentRequestDTO;
import ie.licenta.artassistant.dto.CommentResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements  CommentService {
    @Override
    public List<CommentResponseDTO> getAllComments() {
        return null;
    }

    @Override
    public CommentResponseDTO getCommentById(int id) {
        return null;
    }

    @Override
    public CommentResponseDTO addComment(CommentRequestDTO CommentRequestDTO) {
        return null;
    }

    @Override
    public CommentResponseDTO updateComment(int id, CommentRequestDTO CommentRequestDTO) {
        return null;
    }

    @Override
    public void deleteCommentById(int id) {

    }

    @Override
    public List<CommentResponseDTO> getAllCommentsByArtwork(int artworkId) {
        return null;
    }
}

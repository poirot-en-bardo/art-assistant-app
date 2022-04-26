package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.GenreRequestDTO;
import ie.licenta.artassistant.dto.GenreResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
 public class GenreServiceImpl implements GenreService {
    @Override
    public List<GenreResponseDTO> getAllGenres() {
        return null;
    }

    @Override
    public GenreResponseDTO getGenreById(int id) {
        return null;
    }

    @Override
    public GenreResponseDTO addGenre(GenreRequestDTO GenreRequestDTO) {
        return null;
    }

    @Override
    public GenreResponseDTO updateGenre(int id, GenreRequestDTO GenreRequestDTO) {
        return null;
    }

    @Override
    public void deleteGenreById(int id) {

    }
}

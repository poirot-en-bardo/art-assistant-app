package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.GenreRequestDTO;
import ie.licenta.artassistant.dto.GenreResponseDTO;

import java.util.List;

public interface GenreService {

    List<GenreResponseDTO> getAllGenres();

    GenreResponseDTO getGenreById(int id);

    GenreResponseDTO addGenre(GenreRequestDTO GenreRequestDTO);

    GenreResponseDTO updateGenre(int id, GenreRequestDTO GenreRequestDTO);

    void deleteGenreById(int id);
}

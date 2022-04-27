package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.GenreRequestDTO;
import ie.licenta.artassistant.dto.GenreResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.GenreEntity;
import ie.licenta.artassistant.persistence.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
 public class GenreServiceImpl implements GenreService {
    
    private final GenreRepository genreRepository;
    private final ArtMapper artMapper;
    
    @Override
    public List<GenreResponseDTO> getAllGenres() {
        return null;
    }

    @Override
    public GenreResponseDTO getGenreById(int id) {
        GenreEntity Genre = genreRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_06_GENRE_NOT_FOUND));
        return artMapper.genreEntityToGenreResponseDTO(Genre);
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

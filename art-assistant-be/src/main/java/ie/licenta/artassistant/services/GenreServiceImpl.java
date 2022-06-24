package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.GenreRequestDTO;
import ie.licenta.artassistant.dto.GenreResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.CountryEntity;
import ie.licenta.artassistant.models.GenreEntity;
import ie.licenta.artassistant.persistence.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
 public class GenreServiceImpl implements GenreService {
    
    private final GenreRepository genreRepository;
    private final ArtMapper artMapper;
    
    @Override
    public List<GenreResponseDTO> getAllGenres() {

        Optional<List<GenreEntity>> genreListOptional = Optional.of(genreRepository.findAllByOrderByNameAsc());
        if (genreListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_06_GENRE_NOT_FOUND);
        }
        return artMapper
                .genreEntityListToGenreResponseDTOList(genreListOptional.get());
    }

    @Override
    public GenreResponseDTO getGenreById(int id) {
        GenreEntity Genre = genreRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_06_GENRE_NOT_FOUND));
        return artMapper.genreEntityToGenreResponseDTO(Genre);
    }

    @Override
    @Transactional
    public GenreResponseDTO addGenre(GenreRequestDTO genreRequestDTO) {
        return artMapper.genreEntityToGenreResponseDTO(genreRepository.save(
                artMapper.genreRequestDTOToGenreEntity(genreRequestDTO)));
    }

    @Override
    @Transactional
    public GenreResponseDTO updateGenre(int id, GenreRequestDTO genreRequestDTO) {
        GenreEntity oldGenre = genreRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_06_GENRE_NOT_FOUND));
        if (oldGenre == null) {
            return null;
        }
        else {
            GenreEntity genreEntity = artMapper.genreRequestDTOToGenreEntityWithId(id, genreRequestDTO);
            return artMapper.genreEntityToGenreResponseDTO(genreRepository.save(genreEntity));
        }
    }

    @Override
    public void deleteGenreById(int id) {

    }
}

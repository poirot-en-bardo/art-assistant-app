package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.ArtistRequestDTO;
import ie.licenta.artassistant.dto.ArtistResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.ArtistEntity;
import ie.licenta.artassistant.persistence.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtMapper artMapper;

    @Override
    public List<ArtistResponseDTO> getAllArtists() {
        return null;
    }

    @Override
    public ArtistResponseDTO getArtistById(int id) {
        ArtistEntity artist = artistRepository.findById(id).orElseThrow(() ->
            new ArtNotFoundException(ErrorCode.ERR_02_ARTIST_NOT_FOUND));
        return artMapper.artistEntityToArtistResponseDTO(artist);
    }

    @Override
    @Transactional
    public ArtistResponseDTO addArtist(ArtistRequestDTO artistRequestDTO) {
        return artMapper.artistEntityToArtistResponseDTO(artistRepository.save(
                artMapper.artistRequestDTOToArtistEntity(artistRequestDTO)
        ));
    }

    @Override
    @Transactional
    public ArtistResponseDTO updateArtist(int id, ArtistRequestDTO artistRequestDTO) {
        ArtistEntity oldArtist = artistRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_02_ARTIST_NOT_FOUND));
        if (oldArtist == null) {
            return null;
        } else {
            ArtistEntity artistEntity = artMapper.artistRequestDTOToArtistEntityWithId(id, artistRequestDTO);
            return artMapper.artistEntityToArtistResponseDTO(artistRepository.save(artistEntity));
        }
    }

    @Override
    @Transactional
    public void deleteArtistById(int id) {
        ArtistEntity artist = artistRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_02_ARTIST_NOT_FOUND));
        artistRepository.deleteById(id);
    }
}

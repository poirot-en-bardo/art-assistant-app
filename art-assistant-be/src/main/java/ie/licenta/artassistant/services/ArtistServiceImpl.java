package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.ArtistRequestDTO;
import ie.licenta.artassistant.dto.ArtistResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.persistence.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public ArtistResponseDTO addArtist(ArtistRequestDTO artistRequestDTO) {
        return null;
    }

    @Override
    public ArtistResponseDTO updateArtist(int id, ArtistRequestDTO artistRequestDTO) {
        return null;
    }

    @Override
    public void deleteArtistById(int id) {

    }
}

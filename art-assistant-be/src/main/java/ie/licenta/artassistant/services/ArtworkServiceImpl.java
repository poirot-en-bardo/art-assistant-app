package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.ArtworkRequestDTO;
import ie.licenta.artassistant.dto.ArtworkResponseDTO;
import ie.licenta.artassistant.models.RoomIdEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtworkServiceImpl implements  ArtworkService {
    @Override
    public List<ArtworkResponseDTO> getAllArtworks() {
        return null;
    }

    @Override
    public ArtworkResponseDTO getArtworkById(int id) {
        return null;
    }

    @Override
    public ArtworkResponseDTO addArtwork(ArtworkRequestDTO ArtworkRequestDTO) {
        return null;
    }

    @Override
    public ArtworkResponseDTO updateArtwork(int id, ArtworkRequestDTO artworkRequestDTO) {
        return null;
    }

    @Override
    public void deleteArtworkById(int id) {

    }

    @Override
    public List<ArtworkResponseDTO> getAllArtworksByArtistId(int artistId) {
        return null;
    }

    @Override
    public List<ArtworkResponseDTO> getAllArtworksByRoomId(RoomIdEntity roomIdEntity) {
        return null;
    }

    @Override
    public List<ArtworkResponseDTO> getAllArtworksByGenreId(int genreId) {
        return null;
    }
}

package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.ArtistResponseDTO;
import ie.licenta.artassistant.dto.ArtworkRequestDTO;
import ie.licenta.artassistant.dto.ArtworkResponseDTO;
import ie.licenta.artassistant.models.ArtistEntity;

import java.util.List;

public interface ArtworkService {
    List<ArtworkResponseDTO> getAllArtworks();

    ArtworkResponseDTO getArtworkById(int id);

    ArtworkResponseDTO addArtwork(ArtworkRequestDTO ArtworkRequestDTO);

    ArtworkResponseDTO updateArtwork(int id, ArtworkRequestDTO artworkRequestDTO);

    void deleteArtworkById(int id);

    List<ArtworkResponseDTO> getAllArtworksByArtistId(int artistId);

    List<ArtworkResponseDTO> getAllArtworksByRoomIdOrderByPosition(int roomNumber);

    List<ArtworkResponseDTO> getAllArtworksByGenreId(int genreId);

    List<Integer> getListOfIds();

    ArtistResponseDTO getArtistByArtworkId(int artworkId);

}

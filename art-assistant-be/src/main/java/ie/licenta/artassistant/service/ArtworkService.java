package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.ArtworkRequestDTO;
import ie.licenta.artassistant.dto.ArtworkResponseDTO;
import ie.licenta.artassistant.models.RoomIdEntity;

import java.util.List;

public interface ArtworkService {
    List<ArtworkResponseDTO> getAllArtworks();

    ArtworkResponseDTO getArtworkById(int id);

    ArtworkResponseDTO addArtwork(ArtworkRequestDTO ArtworkRequestDTO);

    ArtworkResponseDTO updateArtwork(int id, ArtworkRequestDTO artworkRequestDTO);

    void deleteArtworkById(int id);

    List<ArtworkResponseDTO> getAllArtworksByArtistId(int artistId);

    List<ArtworkResponseDTO> getAllArtworksByRoomId(RoomIdEntity roomIdEntity);

    List<ArtworkResponseDTO> getAllArtworksByGenreId(int genreId);




}

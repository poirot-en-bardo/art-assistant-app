package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.ArtworkRequestDTO;
import ie.licenta.artassistant.dto.ArtworkResponseDTO;

import java.util.List;

public interface ArtworkService {
    List<ArtworkResponseDTO> getAllArtworks();

    ArtworkResponseDTO getArtworkById(int id);

    ArtworkResponseDTO addArtwork(ArtworkRequestDTO ArtworkRequestDTO);

    ArtworkResponseDTO updateArtwork(int id, ArtworkRequestDTO artworkRequestDTO);

    void deleteArtworkById(int id);

    List<ArtworkResponseDTO> getAllArtworksByArtistId(int artistId);

    List<ArtworkResponseDTO> getAllArtworksByGalleryIdAndRoomNumber(int galleryId, int roomNumber);

    List<ArtworkResponseDTO> getAllArtworksByGenreId(int genreId);




}

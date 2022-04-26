package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.ArtistRequestDTO;
import ie.licenta.artassistant.dto.ArtistResponseDTO;

import java.util.List;

public interface ArtistService {

    List<ArtistResponseDTO> getAllArtists();

    ArtistResponseDTO getArtistById(int id);

    ArtistResponseDTO addArtist(ArtistRequestDTO artistRequestDTO);

    ArtistResponseDTO updateArtist(int id, ArtistRequestDTO artistRequestDTO);

    void deleteArtistById(int id);
}

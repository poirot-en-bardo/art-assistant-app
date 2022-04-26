package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.FavouriteArtistRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtistResponseDTO;
import ie.licenta.artassistant.models.FavouriteArtistIdEntity;

import java.util.List;

public interface FavouriteArtistService {

    List<FavouriteArtistResponseDTO> getFavouriteArtistsByUserId(int id);

    FavouriteArtistResponseDTO addFavouriteArtist(FavouriteArtistRequestDTO FavouriteArtistRequestDTO);

    void deleteFavouriteArtistById(FavouriteArtistIdEntity id);
}

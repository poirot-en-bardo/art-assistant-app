package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.FavouriteArtworkRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtworkResponseDTO;
import ie.licenta.artassistant.models.FavouriteArtworkIdEntity;

import java.util.List;

public interface FavouriteArtworkService {
    List<FavouriteArtworkResponseDTO> getFavouriteArtworksByUserId(int id);

    FavouriteArtworkResponseDTO addFavouriteArtwork(int userId, FavouriteArtworkRequestDTO FavouriteArtworkRequestDTO);

    void deleteFavouriteArtworkById(FavouriteArtworkIdEntity id);
}

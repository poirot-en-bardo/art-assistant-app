package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.FavouriteArtwork;
import ie.licenta.artassistant.dto.FavouriteArtworkRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtworkResponseDTO;
import ie.licenta.artassistant.models.FavouriteArtworkIdEntity;

import java.util.List;

public interface FavouriteArtworkService {
    List<FavouriteArtwork> getFavouriteArtworksByUserId(int id);

    FavouriteArtwork addFavouriteArtwork(FavouriteArtworkRequestDTO favouriteArtworkRequestDTO);

    void deleteFavouriteArtworkById(FavouriteArtworkIdEntity id);
}

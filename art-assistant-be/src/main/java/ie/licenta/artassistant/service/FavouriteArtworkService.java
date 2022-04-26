package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.FavouriteArtworkRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtworkResponseDTO;
import ie.licenta.artassistant.models.FavouriteArtworkIdEntity;

import java.util.List;

public interface FavouriteArtworkService {
    List<FavouriteArtworkResponseDTO> getFavouriteArtworksByUserId(int id);

    FavouriteArtworkResponseDTO addFavouriteArtwork(FavouriteArtworkRequestDTO FavouriteArtworkRequestDTO);

    void deleteFavouriteArtworkById(FavouriteArtworkIdEntity id);
}

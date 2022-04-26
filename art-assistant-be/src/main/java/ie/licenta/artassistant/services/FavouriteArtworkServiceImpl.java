package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.FavouriteArtworkRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtworkResponseDTO;
import ie.licenta.artassistant.models.FavouriteArtworkIdEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavouriteArtworkServiceImpl implements FavouriteArtworkService {
    @Override
    public List<FavouriteArtworkResponseDTO> getFavouriteArtworksByUserId(int id) {
        return null;
    }

    @Override
    public FavouriteArtworkResponseDTO addFavouriteArtwork(FavouriteArtworkRequestDTO FavouriteArtworkRequestDTO) {
        return null;
    }

    @Override
    public void deleteFavouriteArtworkById(FavouriteArtworkIdEntity id) {

    }
}

package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.FavouriteArtistRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtistResponseDTO;
import ie.licenta.artassistant.models.FavouriteArtistIdEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavouriteArtistServiceImpl implements FavouriteArtistService {
    @Override
    public List<FavouriteArtistResponseDTO> getFavouriteArtistsByUserId(int id) {
        return null;
    }

    @Override
    public FavouriteArtistResponseDTO addFavouriteArtist(FavouriteArtistRequestDTO FavouriteArtistRequestDTO) {
        return null;
    }

    @Override
    public void deleteFavouriteArtistById(FavouriteArtistIdEntity id) {

    }
}

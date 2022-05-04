package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.FavouriteArtistRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtistResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.FavouriteArtistEntity;
import ie.licenta.artassistant.models.FavouriteArtistIdEntity;
import ie.licenta.artassistant.persistence.FavouriteArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavouriteArtistServiceImpl implements FavouriteArtistService {

    private final FavouriteArtistRepository favouriteArtistRepository;
    private final ArtMapper artMapper;

    @Override
    public List<FavouriteArtistResponseDTO> getFavouriteArtistsByUserId(int id) {
        Optional<List<FavouriteArtistEntity>> favouriteListOptional = Optional.ofNullable(favouriteArtistRepository
                .findAllByUserId(id));
        if (favouriteListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_01_RESOURCE_NOT_FOUND);
        }
        return artMapper.favouriteArtistEntityListToFavouriteArtistResponseDTOList(favouriteListOptional.get());
    }


    @Override
    public FavouriteArtistResponseDTO addFavouriteArtist(int userId, FavouriteArtistRequestDTO favouriteArtistRequestDTO) {
        FavouriteArtistEntity favouriteArtistEntity = artMapper.favouriteArtistRequestDTOToFavouriteArtistEntity(favouriteArtistRequestDTO);
        favouriteArtistEntity.setUserId(userId);
        return artMapper.favouriteArtistEntityToFavouriteArtistResponseDTO(favouriteArtistRepository.save(favouriteArtistEntity));
    }

    @Override
    public void deleteFavouriteArtistById(FavouriteArtistIdEntity idEntity) {
        Optional<FavouriteArtistEntity> favouriteOptional = Optional.ofNullable(favouriteArtistRepository
                .findFavouriteArtistEntityByUserIdAndArtistId(idEntity.getUserId(), idEntity.getArtistId()));
        if (favouriteOptional.isPresent()) {
            favouriteArtistRepository.deleteFavouriteArtistEntityByUserIdAndArtistId(
                    idEntity.getUserId(), idEntity.getArtistId());
        } else {
            throw new ArtNotFoundException(ErrorCode.ERR_01_RESOURCE_NOT_FOUND);
        }
    }
}

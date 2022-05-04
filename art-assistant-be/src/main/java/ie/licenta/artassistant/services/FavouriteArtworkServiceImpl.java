package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.FavouriteArtworkRequestDTO;
import ie.licenta.artassistant.dto.FavouriteArtworkResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.FavouriteArtworkEntity;
import ie.licenta.artassistant.models.FavouriteArtworkIdEntity;
import ie.licenta.artassistant.persistence.FavouriteArtworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavouriteArtworkServiceImpl implements FavouriteArtworkService {

    private FavouriteArtworkRepository favouriteArtworkRepository;
    private ArtMapper artMapper;

    @Override
    public List<FavouriteArtworkResponseDTO> getFavouriteArtworksByUserId(int id) {
        Optional<List<FavouriteArtworkEntity>> favouriteListOptional = Optional.ofNullable(favouriteArtworkRepository
                .findAllByUserId(id));
        if (favouriteListOptional.isEmpty()) {
            throw new ArtNotFoundException(ErrorCode.ERR_01_RESOURCE_NOT_FOUND);
        }
        return artMapper.favouriteArtworkEntityListToFavouriteArtworkResponseDTOList(favouriteListOptional.get());    }

    @Override
    public FavouriteArtworkResponseDTO addFavouriteArtwork(int userId, FavouriteArtworkRequestDTO favouriteArtworkRequestDTO) {
        FavouriteArtworkEntity favouriteArtworkEntity = artMapper
                .favouriteArtworkRequestDTOToFavouriteArtworkEntity(favouriteArtworkRequestDTO);
        favouriteArtworkEntity.setUserId(userId);
        return artMapper.favouriteArtworkEntityToFavouriteArtworkResponseDTO(
                favouriteArtworkRepository.save(favouriteArtworkEntity));
    }

    @Override
    public void deleteFavouriteArtworkById(FavouriteArtworkIdEntity idEntity) {
        Optional<FavouriteArtworkEntity> favouriteOptional = Optional.ofNullable(favouriteArtworkRepository
                .findFavouriteArtworkEntityByUserIdAndArtworkId(idEntity.getUserId(), idEntity.getArtworkId()));
        if (favouriteOptional.isPresent()) {
            favouriteArtworkRepository.deleteFavouriteArtworkEntityByUserIdAndArtworkId(
                    idEntity.getUserId(), idEntity.getArtworkId());
        } else {
            throw new ArtNotFoundException(ErrorCode.ERR_01_RESOURCE_NOT_FOUND);
        }
    }
}

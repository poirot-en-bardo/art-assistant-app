package ie.licenta.artassistant.mappers;

import ie.licenta.artassistant.security.Role;
import ie.licenta.artassistant.dto.*;
import ie.licenta.artassistant.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtMapper {

    @Mappings({
            @Mapping(target = "countryName", source = "country.name")
    })
    ArtistResponseDTO artistEntityToArtistResponseDTO(ArtistEntity artistEntity);

    @Mappings({
            @Mapping(target = "country.id", source = "countryId")
    })
    ArtistEntity artistRequestDTOToArtistEntity(ArtistRequestDTO artistRequestDTO);

    @Mappings({
            @Mapping(target = "artistFirstName", source = "artist.firstName"),
            @Mapping(target = "artistLastName", source = "artist.lastName"),
            @Mapping(target = "genreName", source = "genre.name")
    })
    ArtworkResponseDTO artworkEntityToArtworkResponseDTO(ArtworkEntity artworkEntity);

    @Mappings({
            @Mapping(target = "artistFirstName", source = "artist.firstName"),
            @Mapping(target = "artistLastName", source = "artist.lastName"),
            @Mapping(target = "genreName", source = "genre.name")
    })
    List<ArtworkResponseDTO> artworkEntityListToArtworkResponseDTOList(List<ArtworkEntity> artworkEntityList);

    @Mappings({
            @Mapping(target = "artist.id", source = "artistId"),
            @Mapping(target = "genre.id", source = "genreId"),
            @Mapping(target = "gallery.id", source = "galleryId")
    })
    ArtworkEntity artworkRequestDTOToArtworkEntity(ArtworkRequestDTO artworkRequestDTO);

    @Mappings({
    @Mapping(target = "id", source = "artworkId"),
    @Mapping(target = "artist.id", source = "artworkRequestDTO.artistId"),
    @Mapping(target = "genre.id", source = "artworkRequestDTO.genreId"),
    @Mapping(target = "gallery.id", source = "artworkRequestDTO.galleryId")
    })
    ArtworkEntity artworkRequestDTOToArtworkEntityWithId(Integer artworkId, ArtworkRequestDTO artworkRequestDTO);


    @Mappings({
            @Mapping(target = "userFirstName", source = "user.firstName"),
            @Mapping(target = "userLastName", source = "user.lastName")
    })
    CommentResponseDTO commentEntityToCommentResponseDTO(CommentEntity commentEntity);
    List<CommentResponseDTO> commentEntityListToCommentResponseDTOList(List<CommentEntity> commentEntity);
    CommentEntity commentRequestDTOToCommentEntity(CommentRequestDTO commentRequestDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CountryResponseDTO countryEntityToCountryResponseDTO(CountryEntity countryEntity);
    CountryEntity countryRequestDTOToCountryEntity(CountryRequestDTO countryRequestDTO);

    FavouriteArtistResponseDTO favouriteArtistEntityToFavouriteArtistResponseDTO(FavouriteArtistEntity favouriteArtistEntity);
    List<FavouriteArtistResponseDTO> favouriteArtistEntityListToFavouriteArtistResponseDTOList(List<FavouriteArtistEntity> favouriteArtistEntity);
    FavouriteArtistEntity favouriteArtistRequestDTOToFavouriteArtistEntity(FavouriteArtistRequestDTO favouriteArtistRequestDTO);

    FavouriteArtworkResponseDTO favouriteArtworkEntityToFavouriteArtworkResponseDTO(FavouriteArtworkEntity favouriteArtworkEntity);
    List<FavouriteArtworkResponseDTO> favouriteArtworkEntityListToFavouriteArtworkResponseDTOList(List<FavouriteArtworkEntity> favouriteArtworkEntity);
    FavouriteArtworkEntity favouriteArtworkRequestDTOToFavouriteArtworkEntity(FavouriteArtworkRequestDTO favouriteArtworkRequestDTO);

    GalleryResponseDTO galleryEntityToGalleryResponseDTO(GalleryEntity galleryEntity);
    List<GalleryResponseDTO> galleryEntityListToGalleryResponseDTOList(List<GalleryEntity> galleryEntity);
    GalleryEntity galleryRequestDTOToGalleryEntity(GalleryRequestDTO galleryRequestDTO);

    GenreResponseDTO genreEntityToGenreResponseDTO(GenreEntity genreEntity);
    GenreEntity genreRequestDTOToGenreEntity(GenreRequestDTO genreRequestDTO);

    @Mappings({
            @Mapping(target = "countryName", source = "country.name")
    })
    MuseumResponseDTO museumEntityToMuseumResponseDTO(MuseumEntity museumEntity);
    @Mappings({
            @Mapping(target = "countryName", source = "country.name")
    })
    List<MuseumResponseDTO> museumEntityListToMuseumResponseDTOList(List<MuseumEntity> museumEntity);
    MuseumEntity museumRequestDTOToMuseumEntity(MuseumRequestDTO museumRequestDTO);


    UserEntity userRequestDTOToUserEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "roles", source = "roleList")
    UserResponseDTO userEntityAndRolesToUserReponseDTO(UserEntity userEntity, List<Role> roleList);

    @Mapping(target = "roles", ignore = true)
    UserEntity signUpRequestDTOToUserEntity(SignUpRequestDTO signUpRequestDTO);
}

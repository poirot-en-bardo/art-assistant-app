package ie.licenta.artassistant.mappers;

import ie.licenta.artassistant.models.RoleName;
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
            @Mapping(target = "countryName", source = "country.name")
    })
    List<ArtistResponseDTO> artistEntityListToArtistResponseDTOList(List<ArtistEntity> artistEntity);


    @Mappings({
            @Mapping(target = "country.id", source = "countryId")
    })
    ArtistEntity artistRequestDTOToArtistEntity(ArtistRequestDTO artistRequestDTO);

    @Mappings({
            @Mapping(target = "id", source = "artistId"),
            @Mapping(target = "country.id", source = "artistRequestDTO.countryId")
    })
    ArtistEntity artistRequestDTOToArtistEntityWithId(Integer artistId, ArtistRequestDTO artistRequestDTO);


    @Mappings({
            @Mapping(target = "artistFirstName", source = "artist.firstName"),
            @Mapping(target = "artistLastName", source = "artist.lastName"),
            @Mapping(target = "artistId", source = "artist.id"),
            @Mapping(target = "genreName", source = "genre.name"),
            @Mapping(target = "genreId", source = "genre.id"),
            @Mapping(target = "roomId", source = "room.id"),
            @Mapping(target = "galleryName", source = "room.gallery.name"),
            @Mapping(target = "museumName", source = "room.gallery.museum.name")

    })
    ArtworkResponseDTO artworkEntityToArtworkResponseDTO(ArtworkEntity artworkEntity);



    @Mappings({
            @Mapping(target = "artistFirstName", source = "artist.firstName"),
            @Mapping(target = "artistLastName", source = "artist.lastName"),
            @Mapping(target = "artistId", source = "artist.id"),
            @Mapping(target = "genreName", source = "genre.name"),
            @Mapping(target = "genreId", source = "genre.id"),
            @Mapping(target = "roomId", source = "room.id"),
            @Mapping(target = "galleryName", source = "room.gallery.name"),
            @Mapping(target = "museumName", source = "room.gallery.museum.name")

    })
    List<ArtworkResponseDTO> artworkEntityListToArtworkResponseDTOList(List<ArtworkEntity> artworkEntityList);


    List<FavouriteArtwork> artworkResponseDTOListToFavouriteArtworkList(List<ArtworkResponseDTO> artworkResponseDTOS);


    @Mappings({
            @Mapping(target = "artist.id", source = "artistId"),
            @Mapping(target = "genre.id", source = "genreId"),
            @Mapping(target = "room.id", source = "roomId")
    })
    ArtworkEntity artworkRequestDTOToArtworkEntity(ArtworkRequestDTO artworkRequestDTO);

    @Mappings({
    @Mapping(target = "id", source = "artworkId"),
    @Mapping(target = "artist.id", source = "artworkRequestDTO.artistId"),
    @Mapping(target = "genre.id", source = "artworkRequestDTO.genreId"),
    @Mapping(target = "room.id", source = "artworkRequestDTO.roomId")
    })
    ArtworkEntity artworkRequestDTOToArtworkEntityWithId(Integer artworkId, ArtworkRequestDTO artworkRequestDTO);


    @Mappings({
            @Mapping(target = "userFirstName", source = "user.firstName"),
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "userId", source = "user.id")
    })
    CommentResponseDTO commentEntityToCommentResponseDTO(CommentEntity commentEntity);

    @Mappings({
            @Mapping(target = "userFirstName", source = "user.firstName"),
            @Mapping(target = "userLastName", source = "user.lastName"),
            @Mapping(target = "userId", source = "user.id")
    })
    List<CommentResponseDTO> commentEntityListToCommentResponseDTOList(List<CommentEntity> commentEntity);
    @Mappings({
            @Mapping(target = "user.id", source = "userId"),
            @Mapping(target = "artwork.id", source = "artworkId")
    })
    CommentEntity commentRequestDTOToCommentEntity(CommentRequestDTO commentRequestDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CountryResponseDTO countryEntityToCountryResponseDTO(CountryEntity countryEntity);
    List<CountryResponseDTO> countryEntityListToCountryResponseDTOList(List<CountryEntity> countryEntity);
    CountryEntity countryRequestDTOToCountryEntity(CountryRequestDTO countryRequestDTO);



    @Mappings({
            @Mapping(target = "artistFirstName", source = "artist.firstName"),
            @Mapping(target = "artistLastName", source = "artist.lastName"),
            @Mapping(target = "museumName", source = "room.gallery.museum.name")

    })
    FavouriteArtwork artworkEntityToFavouriteArtwork(ArtworkEntity artworkEntity);

    @Mappings({
            @Mapping(target = "artistFirstName", source = "artist.firstName"),
            @Mapping(target = "artistLastName", source = "artist.lastName"),
            @Mapping(target = "museumName", source = "room.gallery.museum.name")

    })
    List<FavouriteArtwork> artworkEntityListToFavouriteArtworkList(List<ArtworkEntity> artworkEntity);

    FavouriteArtistResponseDTO favouriteArtistEntityToFavouriteArtistResponseDTO(FavouriteArtistEntity favouriteArtistEntity);
    List<FavouriteArtistResponseDTO> favouriteArtistEntityListToFavouriteArtistResponseDTOList(List<FavouriteArtistEntity> favouriteArtistEntity);
    FavouriteArtistEntity favouriteArtistRequestDTOToFavouriteArtistEntity(FavouriteArtistRequestDTO favouriteArtistRequestDTO);

    FavouriteArtworkResponseDTO favouriteArtworkEntityToFavouriteArtworkResponseDTO(FavouriteArtworkEntity favouriteArtworkEntity);


//    List<FavouriteArtworkResponseDTO> favouriteArtworkListToFavouriteArtworkResponseDTOList(Integer userId, List<FavouriteArtwork> favouriteArtworks);
    FavouriteArtworkEntity favouriteArtworkRequestDTOToFavouriteArtworkEntity(FavouriteArtworkRequestDTO favouriteArtworkRequestDTO);

    @Mappings({
            @Mapping(target = "museumId", source = "museum.id")
    })
    GalleryResponseDTO galleryEntityToGalleryResponseDTO(GalleryEntity galleryEntity);
    List<GalleryResponseDTO> galleryEntityListToGalleryResponseDTOList(List<GalleryEntity> galleryEntity);
    @Mappings({
            @Mapping(target = "museum.id", source = "galleryRequestDTO.museumId")
    })
    GalleryEntity galleryRequestDTOToGalleryEntity(GalleryRequestDTO galleryRequestDTO);
    @Mappings({
            @Mapping(target = "id", source = "galleryId"),
            @Mapping(target = "museum.id", source = "galleryRequestDTO.museumId")
    })
    GalleryEntity galleryRequestDTOToGalleryEntityWithId(Integer galleryId, GalleryRequestDTO galleryRequestDTO);



    GenreResponseDTO genreEntityToGenreResponseDTO(GenreEntity genreEntity);
    List<GenreResponseDTO> genreEntityListToGenreResponseDTOList(List<GenreEntity> genreEntity);
    GenreEntity genreRequestDTOToGenreEntity(GenreRequestDTO genreRequestDTO);
    @Mappings({
            @Mapping(target = "id", source = "genreId"),
    })
    GenreEntity genreRequestDTOToGenreEntityWithId(Integer genreId, GenreRequestDTO genreRequestDTO);


    @Mappings({
            @Mapping(target = "countryName", source = "country.name"),
            @Mapping(target = "countryId", source = "country.id")
    })
    MuseumResponseDTO museumEntityToMuseumResponseDTO(MuseumEntity museumEntity);

    @Mappings({
            @Mapping(target = "countryName", source = "country.name"),
            @Mapping(target = "countryId", source = "country.id")
    })
    List<MuseumResponseDTO> museumEntityListToMuseumResponseDTOList(List<MuseumEntity> museumEntity);

    @Mappings({
            @Mapping(target = "country.id", source = "museumRequestDTO.countryId")
    })
    MuseumEntity museumRequestDTOToMuseumEntity(MuseumRequestDTO museumRequestDTO);

    @Mappings({
            @Mapping(target = "id", source = "museumId"),
            @Mapping(target = "country.id", source = "museumRequestDTO.countryId")
    })
    MuseumEntity museumRequestDTOToMuseumEntityWithId(Integer museumId, MuseumRequestDTO museumRequestDTO);


    @Mappings({
            @Mapping(target = "galleryId", source = "gallery.id")
    })
    RoomResponseDTO roomEntityToRoomResponseDTO(RoomEntity roomEntity);

    @Mappings({
            @Mapping(target = "galleryId", source = "gallery.id")
    })
    List<RoomResponseDTO> roomEntityListToRoomResponseDTOList(List<RoomEntity> roomEntity);

    @Mappings({
            @Mapping(target = "gallery.id", source = "galleryId")
    })
    RoomEntity roomRequestDTOToRoomEntity(RoomRequestDTO roomRequestDTO);

    @Mappings({
            @Mapping(target = "id", source = "roomId"),
            @Mapping(target = "gallery.id", source = "roomRequestDTO.galleryId")
    })
    RoomEntity roomRequestDTOToRoomEntityWithId(Integer roomId, RoomRequestDTO roomRequestDTO);


    UserEntity userRequestDTOToUserEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "roles", source = "roleList")
    UserResponseDTO userEntityAndRolesToUserResponseDTO(UserEntity userEntity, List<RoleName> roleList);

    @Mapping(target = "roles", ignore = true)
    UserEntity signUpRequestDTOToUserEntity(SignUpRequestDTO signUpRequestDTO);
}

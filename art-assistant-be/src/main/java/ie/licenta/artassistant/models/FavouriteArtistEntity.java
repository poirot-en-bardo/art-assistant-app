package ie.licenta.artassistant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FAVOURITE_ARTIST")
@IdClass(FavouriteArtistIdEntity.class)
public class FavouriteArtistEntity {
    @Id
    @Column(name = "USER_ID")
    private int userId;

    @Id
    @Column(name = "ARTIST_ID")
    private int artistId;
}

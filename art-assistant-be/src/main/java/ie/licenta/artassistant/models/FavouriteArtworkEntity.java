package ie.licenta.artassistant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FAVOURITE_ARTWORK")
@IdClass(FavouriteArtworkIdEntity.class)
public class FavouriteArtworkEntity {
    @Id
    @Column(name = "USER_ID")
    private int userId;

    @Id
    @Column(name = "ARTWORK_ID")
    private int artworkId;
}

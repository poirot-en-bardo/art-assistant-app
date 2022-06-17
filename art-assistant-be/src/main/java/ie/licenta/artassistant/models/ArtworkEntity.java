package ie.licenta.artassistant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ARTWORK")
public class ArtworkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, unique = true)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "TECHNIQUE")
    private String technique;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_PATH")
    private byte[] imagePath;

    @Column(name = "POSITION")
    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTIST_ID", referencedColumnName = "ID")
    private ArtistEntity artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID")
    private GenreEntity genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")
    private RoomEntity room;

    @OneToMany(mappedBy = "artwork", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
}

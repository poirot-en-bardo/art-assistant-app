package ie.licenta.artassistant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROOM")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private int id;

    @Column(name = "FLOOR")
    private int floor;

    @Column(name = "MAP")
    private byte[] map;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GALLERY_ID", referencedColumnName = "ID")
//    @JsonIgnore
    private GalleryEntity gallery;
}

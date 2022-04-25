package ie.licenta.artassistant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROOM")
public class RoomEntity {

    @Id
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "FLOOR_NUMBER")
    private int floorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GALLERY_ID", referencedColumnName = "ID")
    private GalleryEntity gallery;
}

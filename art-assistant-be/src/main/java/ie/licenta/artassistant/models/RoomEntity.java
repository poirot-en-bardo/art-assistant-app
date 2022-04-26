package ie.licenta.artassistant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RoomIdEntity.class)
@Table(name = "ROOM")
public class RoomEntity {

    @Id
    @Column(name = "ID")
    private int id;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GALLERY_ID", referencedColumnName = "ID")
    private GalleryEntity gallery;

    @Column(name = "FLOOR_NUMBER")
    private int floorNumber;

}

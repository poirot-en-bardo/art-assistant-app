package ie.licenta.artassistant.dto;

import ie.licenta.artassistant.models.GalleryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {

    private int floor;

    private int number;

    private byte[] map;

    private int galleryId;
}

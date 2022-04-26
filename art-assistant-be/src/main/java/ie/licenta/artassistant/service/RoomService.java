package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.RoomRequestDTO;
import ie.licenta.artassistant.dto.RoomResponseDTO;
import ie.licenta.artassistant.models.RoomIdEntity;

import java.util.List;

public interface RoomService {

    RoomResponseDTO getRoomById(RoomIdEntity roomIdEntity);

    List<RoomResponseDTO> getAllRoomsByGalleryId(int galleryId);

    RoomResponseDTO addRoom(RoomRequestDTO RoomRequestDTO);

    RoomResponseDTO updateRoom(RoomIdEntity roomIdEntity, RoomRequestDTO RoomRequestDTO);

    void deleteRoomById(RoomIdEntity roomIdEntity);
}

package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.RoomRequestDTO;
import ie.licenta.artassistant.dto.RoomResponseDTO;

import java.util.List;

public interface RoomService {

    RoomResponseDTO getRoomById(int id);

    List<RoomResponseDTO> getAllRoomsByGalleryId(int galleryId);

    RoomResponseDTO addRoom(RoomRequestDTO roomRequestDTO);

    RoomResponseDTO updateRoom(int id, RoomRequestDTO roomRequestDTO);

    void deleteRoomById(int id);
}

package dio.projeto_dio_CloudParking.controller;

import dio.projeto_dio_CloudParking.controller.mapper.ParkingMapper;
import dio.projeto_dio_CloudParking.controller.dto.ParkingCreateDTO;
import dio.projeto_dio_CloudParking.controller.dto.ParkingDTO;
import dio.projeto_dio_CloudParking.model.Parking;
import dio.projeto_dio_CloudParking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/parkings")public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingMapper parkingMapper;

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> getAllParkings() {
        List<Parking> parkings = parkingService.findAll();
        List<ParkingDTO> parkingDTOs = parkingMapper.toParkingDTOList(parkings);
        return ResponseEntity.ok(parkingDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> getParkingById(@PathVariable Long id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(parkingDTO);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> createParking(@RequestBody ParkingCreateDTO parkingCreateDTO) {
        Parking parking = parkingMapper.toParking(parkingCreateDTO);
        parking = parkingService.create(parking);
        ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(parkingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> updateParking(@PathVariable Long id, @RequestBody ParkingDTO parkingDTO) {
        Parking existingParking = parkingService.findById(id);
        parkingMapper.updateParkingFromDTO(parkingDTO, existingParking);
        Parking updatedParking = parkingService.update(id, existingParking);
        ParkingDTO updatedParkingDTO = parkingMapper.toParkingDTO(updatedParking);
        return ResponseEntity.ok(updatedParkingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParking(@PathVariable Long id) {
        parkingService.delete(id);
        return ResponseEntity.ok().build();
    }
}
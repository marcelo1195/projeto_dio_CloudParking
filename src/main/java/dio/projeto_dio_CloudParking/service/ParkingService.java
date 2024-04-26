package dio.projeto_dio_CloudParking.service;

import dio.projeto_dio_CloudParking.service.ParkingCheckOut;
import dio.projeto_dio_CloudParking.model.Parking;
import dio.projeto_dio_CloudParking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingCheckOut parkingCheckOut;

    @Transactional(readOnly = true)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parking findById(Long id) {
        return parkingRepository.findById(id).orElseThrow(() -> new RuntimeException("Parking not found"));
    }

    @Transactional
    public Parking create(Parking parking) {
        parking.setId(null); // Assume ID is auto-generated
        parking.setEntryDate(LocalDateTime.now());
        return parkingRepository.save(parking);
    }

    @Transactional
    public void delete(Long id) {
        findById(id); // Check existence
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking update(Long id, Parking parkingUpdate) {
        Parking parking = findById(id);
        parking.setColor(parkingUpdate.getColor());
        parking.setState(parkingUpdate.getState());
        parking.setModel(parkingUpdate.getModel());
        parking.setLicense(parkingUpdate.getLicense());
        return parkingRepository.save(parking);
    }

    @Transactional
    public Parking checkOut(Long id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(parkingCheckOut.calculateFee(parking.getEntryDate(), parking.getExitDate()));
        return parkingRepository.save(parking);
    }
}

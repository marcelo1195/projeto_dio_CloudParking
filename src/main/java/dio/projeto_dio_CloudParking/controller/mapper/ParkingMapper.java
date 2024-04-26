package dio.projeto_dio_CloudParking.controller.mapper;

import dio.projeto_dio_CloudParking.controller.dto.ParkingCreateDTO;
import dio.projeto_dio_CloudParking.controller.dto.ParkingDTO;
import dio.projeto_dio_CloudParking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ParkingMapper {

    private final ModelMapper modelMapper;

    public ParkingMapper(){
        this.modelMapper = new ModelMapper();
    }

    public ParkingDTO toParkingDTO(Parking parking) {
        return modelMapper.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList){
        return parkingList.stream()
                .map(this::toParkingDTO)
                .collect(Collectors.toList());
    }

    public Parking toParking(ParkingCreateDTO parkingCreateDTO) {
        return modelMapper.map(parkingCreateDTO, Parking.class);
    }

    public void updateParkingFromDTO(ParkingDTO parkingDTO, Parking parking) {
        modelMapper.map(parkingDTO, parking);
    }

}

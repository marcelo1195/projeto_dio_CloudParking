package dio.projeto_dio_CloudParking.controller.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ParkingCreateDTO implements Serializable {
    private String license;
    private String state;
    private String model;
    private String color;
    private String id;
}

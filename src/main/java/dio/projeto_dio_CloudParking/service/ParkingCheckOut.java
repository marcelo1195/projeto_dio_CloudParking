package dio.projeto_dio_CloudParking.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ParkingCheckOut {
    @Value("${parking.hourly-rate}")
    private Double hourlyRate;

    private static final int ONE_HOUR = 60;
    private static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    private static final double ADDITIONAL_HOUR_RATE = 2.00;
    private static final double DAY_RATE = 20.00;

    public Double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime) {
        long minutes = entryTime.until(exitTime, ChronoUnit.MINUTES);
        if (minutes <= ONE_HOUR) {
            return hourlyRate;
        } else if (minutes <= TWENTY_FOUR_HOUR) {
            double fee = hourlyRate;
            int hours = (int) (minutes / ONE_HOUR);
            fee += ADDITIONAL_HOUR_RATE * (hours - 1);
            return fee;
        } else {
            int days = (int) (minutes / TWENTY_FOUR_HOUR);
            return DAY_RATE * days + calculateFee(entryTime.plusDays(days), exitTime);
        }
    }
}

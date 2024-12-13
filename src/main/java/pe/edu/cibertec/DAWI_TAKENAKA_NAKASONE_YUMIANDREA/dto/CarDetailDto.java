package pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.dto;

import java.util.Date;

public record CarDetailDto(
        Integer carId,
        String make,
        String model,
        Integer year,
        String licensePlate,
        String ownerName,
        String ownerContact,
        Date purchaseDate,
        Integer mileage,
        String engineType,
        String color,
        String insuranceCompany,
        String insurancePolicyNumber
) {
}

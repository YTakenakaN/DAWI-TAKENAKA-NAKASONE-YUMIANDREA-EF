package pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.response;

import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars
) {
}

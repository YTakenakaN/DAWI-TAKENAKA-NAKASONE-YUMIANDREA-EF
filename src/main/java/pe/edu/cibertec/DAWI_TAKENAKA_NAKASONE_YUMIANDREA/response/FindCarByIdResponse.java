package pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.response;

import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.dto.CarDetailDto;

public record FindCarByIdResponse(String code,
                                  String error,
                                  CarDetailDto car
) {

}

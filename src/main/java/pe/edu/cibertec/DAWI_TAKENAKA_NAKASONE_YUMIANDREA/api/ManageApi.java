package pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.dto.CarDto;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.response.FindCarByIdResponse;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.response.FindCarsResponse;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.response.CrudCarResponse;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageApi {
    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDto> optional = manageService.getAllCarsById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDto carDto = optional.get();
                    return new FindCarsResponse("01", "", List.of(carDto));
                } else {
                    return new FindCarsResponse("02", "Car not found", null);
                }

            } else {
                List<CarDto> cars = manageService.getAllCars();
                if (!cars.isEmpty())
                    return new FindCarsResponse("01", "", cars);
                else
                    return new FindCarsResponse("03", "Car list not found", cars);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99", "Service not found", null);
        }
    }

    @GetMapping("/detail")
    public FindCarByIdResponse findUserById(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDetailDto> optional = manageService.getCarById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarByIdResponse("01","", carDetailDto);
                } else {
                    return new FindCarByIdResponse("02", "Car not found", null);
                }

            } else
                return new FindCarByIdResponse("03", "Parameter not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarByIdResponse("99", "Service not found", null);

        }
    }


    @PostMapping("/update")
    public CrudCarResponse updateCar(@RequestBody CarDto carDto) {

        try {
            if (manageService.updateCar(carDto)) {
                return new CrudCarResponse("01", "");
            } else {
                return new CrudCarResponse("02", "Car not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new CrudCarResponse("99", "Service not found");
        }
    }

    @GetMapping("/delete/{id}")
    public CrudCarResponse deleteCar(@PathVariable(value = "id") String id) {
        try {
            if(manageService.deleteCarById(Integer.parseInt(id))) {
                return new CrudCarResponse("01", "");
            }else{
                return new CrudCarResponse("02", "Car not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CrudCarResponse("99", "Service not found");
        }
    }

    @PostMapping("/add")
    public CrudCarResponse addCar(@RequestBody CarDetailDto carDetailDto) {

        try {

            manageService.addCar(carDetailDto);
            if (manageService.addCar(carDetailDto)) {
                return new CrudCarResponse("01", "");
            }else{
                return new CrudCarResponse("02", "Car already exists");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CrudCarResponse("99", "Service not found");
        }
    }


}

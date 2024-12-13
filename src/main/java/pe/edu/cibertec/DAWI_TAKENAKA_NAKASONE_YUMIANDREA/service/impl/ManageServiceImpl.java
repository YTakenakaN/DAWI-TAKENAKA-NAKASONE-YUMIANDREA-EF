package pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.dto.CarDto;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.entity.Car;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.repository.CarRepository;
import pe.edu.cibertec.DAWI_TAKENAKA_NAKASONE_YUMIANDREA.service.ManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {

        List<CarDto> cars= new ArrayList<CarDto>();
        Iterable<Car> iterable=carRepository.findAll();
        iterable.forEach(car->{
                    CarDto carDto = new CarDto(
                            car.getCarId(),
                            car.getMake(),
                            car.getModel(),
                            car.getLicensePlate(),
                            car.getEngineType(),
                            car.getColor());
                    cars.add(carDto);
                }
        );
        return cars;
    }

    @Override
    public Optional<CarDto> getAllCarsById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getLicensePlate(),
                car.getEngineType(),
                car.getColor()
                )
        );
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDetailDto(
                car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(car -> {
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setLicensePlate(carDto.licensePlate());
            car.setEngineType(carDto.engineType());
            car.setColor(carDto.color());
            carRepository.save(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.carId());
        if (optional.isPresent())
            return false;
        else {
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(new Date());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            carRepository.save(car);
            return true;
        }
    }

}

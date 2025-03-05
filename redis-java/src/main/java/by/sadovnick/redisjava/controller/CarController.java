package by.sadovnick.redisjava.controller;

import by.sadovnick.redisjava.model.dto.CarDto;
import by.sadovnick.redisjava.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.createCar(carDto));
    }

    @GetMapping("/get")
    public ResponseEntity<CarDto> getCar(@RequestParam String model) {
        return ResponseEntity.ok(carService.findCarById(model));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCar(@RequestParam String model) {
        carService.deleteCarById(model);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<CarDto> updateCar(@RequestParam Integer id, @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.updateCar(id, carDto));
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/randomCar1")
    public ResponseEntity<CarDto> randomCar1() {
        return ResponseEntity.ok(carService.getRandomCar());
    }

    @GetMapping("/randomCar2")
    public ResponseEntity<CarDto> randomCar2() {
        return ResponseEntity.ok(carService.getRandomCar2());
    }

//    @GetMapping("/randomCar3")
//    public ResponseEntity<CarDto> randomCar3() {
//        return ResponseEntity.ok(carService.getCachedCar(new Random().nextInt(1, 10)));
//    }

    //    @PostMapping("/addCarToQueue")
//    public ResponseEntity<Void> addCarToQueue(@RequestBody CarDto carDto) {
//        carService.addCarToQueue(carDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @GetMapping("/getCarInQueue")
//    public ResponseEntity<CarDto> getCarInQueue() {
//        return ResponseEntity.ok(carService.processCar());
//    }
//
//    @GetMapping("/getAllCarInQueue")
//    public ResponseEntity<List<CarDto>> getAllCarsByQueue() {
//        return ResponseEntity.ok(carService.getAllCarsByQueue());
//    }

    //указать канал chat как в конфиге
    @PostMapping("/send-message")
    public ResponseEntity<Void> sendMessage(@RequestParam String channel, @RequestParam String message) {
        carService.sendMessage(channel, message);
        return ResponseEntity.ok().build();
    }
}

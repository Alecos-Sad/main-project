package by.sadovnick.redisjava.service;

import by.sadovnick.redisjava.mapper.CarMapper;
import by.sadovnick.redisjava.model.dto.CarDto;
import by.sadovnick.redisjava.model.entity.Car;
import by.sadovnick.redisjava.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
//    private final RedisTemplate<String, CarDto> redisTemplate;
    private final RedisTemplate<String, String> redisTemplate;

    public CarDto createCar(CarDto carDto) {
        Car car = carRepository.save(carMapper.dtoToEntity(carDto));
        return carMapper.entityToDto(car);
    }

    public CarDto findCarById(String model) {
        Car car = carRepository.findByModel(model).orElseThrow(IllegalArgumentException::new);
        return carMapper.entityToDto(car);
    }

    public void deleteCarById(String model) {
        carRepository.delete(carRepository.findByModel(model).orElseThrow(IllegalArgumentException::new));
    }

    public CarDto updateCar(Integer id, CarDto carDto) {

        Car carInBD = carRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        Car car = Car.builder()
                .brand(carDto.brand() == null ? carInBD.getBrand() : carDto.brand())
                .model(carDto.model() == null ? carInBD.getModel() : carDto.model())
                .year(carDto.year() == null ? carInBD.getYear() : carDto.year())
                .color(carDto.color() == null ? carInBD.getColor() : carDto.color())
                .build();

        Car save = carRepository.save(car);
        return carMapper.entityToDto(save);
    }

    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(carMapper::entityToDto).toList();
    }

    public CarDto getRandomCar() {
        long count = carRepository.count();
        int i = new Random().nextInt(1, (int) count);
        return carMapper.entityToDto(carRepository.findById(i).orElseThrow(IllegalArgumentException::new));
    }

    public CarDto getRandomCar2() {
        List<CarDto> list = carRepository.findAll().stream().map(carMapper::entityToDto).toList();
        return list.get(new Random().nextInt(list.size()));
    }

    //раскомментировать вариант кеша редиса в конфиге и закомментировать первый
    // private final RedisTemplate<String, CarDto> redisTemplate; убрать
//    @Cacheable(value = "cars", key = "#id")
//    public CarDto getCachedCar(Integer id) {
//        return carMapper.entityToDto(carRepository.findById(id).orElseThrow(IllegalArgumentException::new));
//    }

    //редис как очередь - добавление
    //не работает со вторым конфигом для спринга
    // private final RedisTemplate<String, CarDto> redisTemplate; добавить
//    public void addCarToQueue(CarDto carDto) {
//        redisTemplate.opsForList().leftPush("cars", carDto);
//    }

    //редис как очередь - излечение
//    public CarDto processCar(){
//        return redisTemplate.opsForList().rightPop("cars");
//    }

    //редис как очередь получить всю очередь
//    public List<CarDto> getAllCarsByQueue(){
//        return redisTemplate.opsForList().range("cars", 0, -1);
//    }

    public void sendMessage(String channel ,String message) {
        redisTemplate.convertAndSend(channel, message);
    }
}

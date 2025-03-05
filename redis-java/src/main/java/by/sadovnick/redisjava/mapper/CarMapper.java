package by.sadovnick.redisjava.mapper;

import by.sadovnick.redisjava.model.dto.CarDto;
import by.sadovnick.redisjava.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto entityToDto (Car car);

    @Mapping(target = "id", ignore = true)
    Car dtoToEntity (CarDto carDto);

}

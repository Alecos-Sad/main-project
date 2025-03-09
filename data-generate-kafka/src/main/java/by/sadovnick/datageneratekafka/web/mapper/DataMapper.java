package by.sadovnick.datageneratekafka.web.mapper;

import by.sadovnick.datageneratekafka.model.Data;
import by.sadovnick.datageneratekafka.web.dto.DataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {


}

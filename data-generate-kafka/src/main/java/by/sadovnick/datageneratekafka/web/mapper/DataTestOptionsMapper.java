package by.sadovnick.datageneratekafka.web.mapper;

import by.sadovnick.datageneratekafka.model.test.DataTestOptions;
import by.sadovnick.datageneratekafka.model.test.DataTestOptionsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDto> {


}

package by.sadovnick.datastorekafkaredis.web.mapper;

import by.sadovnick.datastorekafkaredis.model.Summary;
import by.sadovnick.datastorekafkaredis.web.dto.SummaryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SummaryMapper extends Mappable<Summary, SummaryDto> {
}

package by.sadovnick.socksapihhru.mapper;

import by.sadovnick.socksapihhru.dto.SockDto;
import by.sadovnick.socksapihhru.model.Sock;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SockMapper {

    Sock dtoToEntity(SockDto sockDto);

    SockDto entityToDto(Sock sock);

    List<SockDto> entityListToDtoList(List<Sock> sockList);

    List<Sock> dtoListToEntityList(List<SockDto> sockDtoList);
}

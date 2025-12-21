package com.technokratos.agona.mapper;

import com.technokratos.agona.dto.PersonDto;
import com.technokratos.agona.dto.PersonDtoWithBooks;
import com.technokratos.agona.model.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    PersonDto toDto(PersonEntity personEntity);

    List<PersonDto> toDto(List<PersonEntity> personEntity);

    PersonEntity toEntity(PersonDto personDto);

    @Mapping(target = "books", source = "books")
    PersonDtoWithBooks toDtoWithBooks(PersonEntity personEntity);

}

package com.technokratos.agona.mapper;

import com.technokratos.agona.dto.BookDto;
import com.technokratos.agona.dto.BookDtoWithPeople;
import com.technokratos.agona.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookDto toDto(BookEntity bookEntity);

    List<BookDto> toDto(List<BookEntity> bookEntity);

    BookEntity toEntity(BookDto bookDto);

    @Mapping(target = "person", source = "person")
    BookDtoWithPeople toDtoWithPeople(BookEntity bookEntity);

}

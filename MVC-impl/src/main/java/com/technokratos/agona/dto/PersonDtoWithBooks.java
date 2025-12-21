package com.technokratos.agona.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDtoWithBooks {

    private UUID id;
    private String fio;
    private String birth;
    private Set<BookDto> books;

}

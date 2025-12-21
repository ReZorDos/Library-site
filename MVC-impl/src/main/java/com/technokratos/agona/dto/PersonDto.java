package com.technokratos.agona.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    private UUID id;
    private String fio;
    private LocalDate birth;

}

package com.technokratos.agona.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "ФИО должно быть в формате: Фамилия Имя Отчество")
    private String fio;

    @NotNull(message = "Дата рождения не должна быть пустым")
    private LocalDate birth;

}

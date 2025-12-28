package com.technokratos.agona.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private UUID id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 симвовлов")
    private String name;

    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 50, message = "Автор должен быть от 2 до 50 симвовлов")
    private String author;

    @NotNull(message = "Год не должен быть пустым")
    @Min(value = 1, message = "Год должен быть не меньше 1")
    private int year;

}

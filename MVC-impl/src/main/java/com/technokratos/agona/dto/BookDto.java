package com.technokratos.agona.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private UUID id;
    private String name;
    private String author;
    private int year;

}

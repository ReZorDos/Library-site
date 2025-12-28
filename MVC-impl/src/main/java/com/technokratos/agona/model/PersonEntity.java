package com.technokratos.agona.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity extends AbstractEntity {

    @Column(name = "fio")
    private String fio;

    @Column(name = "birth")
    private LocalDate birth;

    @OneToMany(mappedBy = "person")
    private Set<BookEntity> books = new HashSet<>();

}

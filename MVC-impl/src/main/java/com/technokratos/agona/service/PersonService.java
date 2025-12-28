package com.technokratos.agona.service;

import com.technokratos.agona.dto.PersonDto;
import com.technokratos.agona.dto.PersonDtoWithBooks;
import com.technokratos.agona.mapper.PersonMapper;
import com.technokratos.agona.model.BookEntity;
import com.technokratos.agona.model.PersonEntity;
import com.technokratos.agona.repository.BookRepository;
import com.technokratos.agona.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final BookService bookService;
    private final PersonMapper personMapper;

    public PersonDto getPersonById(UUID id) {
        Optional<PersonEntity> person = personRepository.findById(id);
        if (person.isPresent()) {
            return personMapper.toDto(person.get());
        }
        return null;
    }

    public PersonDtoWithBooks getPersonWithBooksById(UUID id) {
        Optional<PersonEntity> person = personRepository.findPersonWithBooks(id);
        if (person.isPresent()) {
            return personMapper.toDtoWithBooks(person.get());
        }
        return null;
    }

    public List<PersonDto> getAllPeople() {
        List<PersonEntity> result = personRepository.findAll();
        return personMapper.toDto(result);
    }

    public void createPerson(PersonDto personDto) {
        PersonEntity person = personMapper.toEntity(personDto);
        personRepository.save(person);
    }

    @Transactional
    public void updatePerson(PersonDto personDto, UUID id) {
        Optional<PersonEntity> entity = personRepository.findById(id);
        if (entity.isPresent()) {
            entity.get().setFio(personDto.getFio());
            entity.get().setBirth(personDto.getBirth());
            personRepository.save(entity.get());;
        }
    }

    @Transactional
    public void deletePerson(UUID id) {
        Optional<PersonEntity> entity = personRepository.findById(id);
        if (entity.isPresent()) {
            bookService.releaseAllBooksByPersonId(id);
            personRepository.delete(entity.get());
        }
    }

}

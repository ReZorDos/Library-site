package com.technokratos.agona.util;

import com.technokratos.agona.dto.PersonDto;
import com.technokratos.agona.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {

    private final PersonRepository personRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDto person = (PersonDto) target;
        if (personRepository.findOneByFio(person.getFio()).isPresent()) {
            errors.rejectValue("fio", "", "ФИО уже есть в базе данных");
        }

    }
}

package com.technokratos.agona.controller;

import com.technokratos.agona.dto.PersonDto;
import com.technokratos.agona.dto.PersonDtoWithBooks;
import com.technokratos.agona.service.PersonService;
import com.technokratos.agona.util.PersonValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @GetMapping("/all")
    public String allPeople(Model model) {
        List<PersonDto> peopleList = personService.getAllPeople();
        model.addAttribute("peopleList", peopleList);
        return "all-people.html";
    }

    @GetMapping("/create-person")
    public String newPerson(@ModelAttribute("person") PersonDto personDto) {
        return "create-person.html";
    }

    @PostMapping("/create-person")
    public String createPerson(@ModelAttribute("person") @Valid PersonDto personDto,
                               BindingResult bindingResult) {
        personValidator.validate(personDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "create-person.html";
        }
        personService.createPerson(personDto);
        return "redirect:/people/all";
    }

    @GetMapping("/{id}/update")
    public String updatePerson(@PathVariable("id") UUID id, Model model) {
        PersonDto personDto = personService.getPersonById(id);
        model.addAttribute("personDto", personDto);
        return "update-person.html";
    }

    @PatchMapping("/{id}/update")
    public String updatePerson(@PathVariable("id") UUID id,
                               @ModelAttribute("personDto") @Valid PersonDto personDto,
                               BindingResult bindingResult) {
        personValidator.validate(personDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "update-person.html";
        }
        personService.updatePerson(personDto, id);
        return "redirect:/people/all";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
        return "redirect:/people/all";
    }

    @GetMapping("/{id}")
    public String personPage(@PathVariable("id") UUID id,
                             Model model) {
        PersonDtoWithBooks person = personService.getPersonWithBooksById(id);
        model.addAttribute("person", person);
        return "person.html";
    }

}

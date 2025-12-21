package com.technokratos.agona.controller;

import com.technokratos.agona.dto.PersonDto;
import com.technokratos.agona.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

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
    public String createPerson(@ModelAttribute("person") PersonDto personDto) {
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
                               @ModelAttribute("personDto") PersonDto personDto) {
        personService.updatePerson(personDto, id);
        return "redirect:/people/all";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
        return "redirect:/people/all";
    }

}

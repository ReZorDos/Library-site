package com.technokratos.agona.controller;

import com.technokratos.agona.dto.BookDto;
import com.technokratos.agona.dto.BookDtoWithPeople;
import com.technokratos.agona.dto.PersonDto;
import com.technokratos.agona.service.BookService;
import com.technokratos.agona.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @GetMapping("/all")
    public String allBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "all-books.html";
    }

    @GetMapping("/create-book")
    public String newBook(@ModelAttribute("book") BookDto bookDto) {
        return "create-book.html";
    }

    @PostMapping("/create-book")
    public String createBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/books/all";
    }

    @GetMapping("/{id}/update")
    public String updateBook(@PathVariable("id") UUID id, Model model) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);
        return "update-book.html";
    }

    @PatchMapping("/{id}/update")
    public String updateBook(@PathVariable("id") UUID id,
                             @ModelAttribute BookDto bookDto) {
        bookService.updateBook(bookDto, id);
        return "redirect:/books/all";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") UUID id) {
        bookService.deleteBook(id);
        return "redirect:/books/all";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") UUID id,
                           Model model) {
        BookDtoWithPeople bookDto = bookService.getBookWithPeopleById(id);
        List<PersonDto> people = personService.getAllPeople();
        model.addAttribute("book", bookDto);
        model.addAttribute("people", people);
        return "book.html";
    }

    @PutMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") UUID id,
                              RedirectAttributes redirectAttributes) {
        bookService.releaseBookFromPerson(id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/books/{id}";
    }

    @PutMapping("/{idBook}/assign")
    public String assignBook(@PathVariable("idBook") UUID idBook,
                             @RequestParam("idPerson") UUID idPerson,
                             RedirectAttributes redirectAttributes) {
        bookService.assignBookToPerson(idBook, idPerson);
        redirectAttributes.addAttribute("id", idBook);
        return "redirect:/books/{id}";
    }

}

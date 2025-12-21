package com.technokratos.agona.controller;

import com.technokratos.agona.dto.BookDto;
import com.technokratos.agona.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

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


}

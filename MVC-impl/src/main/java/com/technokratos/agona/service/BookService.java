package com.technokratos.agona.service;

import com.technokratos.agona.dto.BookDto;
import com.technokratos.agona.dto.BookDtoWithPeople;
import com.technokratos.agona.mapper.BookMapper;
import com.technokratos.agona.model.BookEntity;
import com.technokratos.agona.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto getBookById(UUID id) {
        Optional<BookEntity> entity = bookRepository.findById(id);
        if (entity.isPresent()) {
            return bookMapper.toDto(entity.get());
        }
        return null;
    }

    public BookDtoWithPeople getBookWithPeopleById(UUID id) {
        Optional<BookEntity> entity = bookRepository.findById(id);
        if (entity.isPresent()) {
            return bookMapper.toDtoWithPeople(entity.get());
        }
        return null;
    }

    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return bookMapper.toDto(books);
    }

    public void createBook(BookDto bookDto) {
        BookEntity book = bookMapper.toEntity(bookDto);
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(BookDto bookDto, UUID id) {
        Optional<BookEntity> entity = bookRepository.findById(id);
        if (entity.isPresent()) {
            entity.get().setName(bookDto.getName());
            entity.get().setAuthor(bookDto.getAuthor());
            entity.get().setYear(bookDto.getYear());
            bookRepository.save(entity.get());
        }
    }

    @Transactional
    public void deleteBook(UUID id) {
        Optional<BookEntity> entity = bookRepository.findById(id);
        entity.ifPresent(bookRepository::delete);
    }

    @Transactional
    public void releaseBookFromPerson(UUID id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.updatePersonIdToRelease(id);
        }
    }

    @Transactional
    public void assignBookToPerson(UUID bookId, UUID personId) {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            bookRepository.assignBookToPersonById(bookId, personId);
        }
    }

}

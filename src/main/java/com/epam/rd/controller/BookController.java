package com.epam.rd.controller;

import com.epam.rd.dto.BookDto;
import com.epam.rd.exception.BookNotFoundException;
import com.epam.rd.exception.DuplicateBookException;
import com.epam.rd.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/books/{bookId}")
    public BookDto getBookById(@PathVariable int bookId) throws BookNotFoundException {
        return bookService.getBookById(bookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/books")
    public void saveBook(@RequestBody BookDto bookDto) throws DuplicateBookException {
        bookService.saveBook(bookDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/books/{bookId}")
    public void updateBook(@PathVariable int bookId ,@RequestBody BookDto bookDto) throws BookNotFoundException {
        bookService.updateBook(bookId,bookDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable int bookId) throws BookNotFoundException {
        bookService.deleteBook(bookId);
    }
}

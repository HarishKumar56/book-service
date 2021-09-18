package com.epam.rd.service;

import com.epam.rd.dto.BookDto;
import com.epam.rd.exception.BookNotFoundException;
import com.epam.rd.exception.DuplicateBookException;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();

    BookDto getBookById(int bookId) throws BookNotFoundException;

    void saveBook(BookDto bookDto) throws DuplicateBookException;

    void updateBook(int bookId, BookDto bookDto) throws BookNotFoundException;

    void deleteBook(int bookId) throws BookNotFoundException;
}

package com.epam.rd.service;

import com.epam.rd.dto.BookDto;
import com.epam.rd.exception.BookNotFoundException;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();

    BookDto getBookById(int bookId) throws BookNotFoundException;
}

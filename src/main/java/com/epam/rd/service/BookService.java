package com.epam.rd.service;

import com.epam.rd.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();

    BookDto getBookById(int bookId);
}

package com.epam.rd.service;

import com.epam.rd.dto.BookDto;
import com.epam.rd.entity.Book;
import com.epam.rd.repository.BookDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookDao bookDao;
    @Override
    public List<BookDto> getAllUsers() {
        List<Book> allBooks = bookDao.findAll();
        List<BookDto> allBooksDto = new ArrayList<>();
        allBooks.forEach(book -> allBooksDto.add(modelMapper.map(book,BookDto.class)));
        return allBooksDto;
    }
}

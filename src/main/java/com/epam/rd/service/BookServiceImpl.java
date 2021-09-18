package com.epam.rd.service;

import com.epam.rd.dto.BookDto;
import com.epam.rd.entity.Book;
import com.epam.rd.exception.BookNotFoundException;
import com.epam.rd.repository.BookDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookDao bookDao;
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> allBooks = bookDao.findAll();
        List<BookDto> allBooksDto = new ArrayList<>();
        allBooks.forEach(book -> allBooksDto.add(modelMapper.map(book,BookDto.class)));
        return allBooksDto;
    }

    @Override
    public BookDto getBookById(int bookId) throws BookNotFoundException {
        Optional<Book> book = bookDao.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book with this Id Not Found");
        }
        return modelMapper.map(book.get() , BookDto.class);
    }

    @Override
    public void saveBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto , Book.class);
        if(bookDao.existsById(book.getBookId())){

        }
        bookDao.save(book);
    }
}

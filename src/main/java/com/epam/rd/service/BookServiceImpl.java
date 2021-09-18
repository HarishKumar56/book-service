package com.epam.rd.service;

import com.epam.rd.dto.BookDto;
import com.epam.rd.entity.Book;
import com.epam.rd.exception.BookNotFoundException;
import com.epam.rd.exception.DuplicateBookException;
import com.epam.rd.repository.BookDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("bookService")
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
    public void saveBook(BookDto bookDto) throws DuplicateBookException {
        Book book = modelMapper.map(bookDto , Book.class);
        if(bookDao.existsById(book.getBookId())){
            throw new DuplicateBookException("Book Already Exist");
        }
        bookDao.save(book);
    }

    @Override
    public void updateBook(int bookId, BookDto bookDto) throws BookNotFoundException {
        BookDto bookDto1 = getBookById(bookId);
        bookDto1.setName(bookDto.getName());
        bookDto1.setAuthor(bookDto.getAuthor());
        bookDto1.setPublisher(bookDto.getPublisher());
        Book book = modelMapper.map(bookDto1 , Book.class);
        bookDao.save(book);
    }

    @Override
    public void deleteBook(int bookId) throws BookNotFoundException {
        BookDto bookDto = getBookById(bookId);
        Book book = modelMapper.map(bookDto , Book.class);
        bookDao.delete(book);
    }
}

package com.epam.rd.service;

import com.epam.rd.dto.BookDto;
import com.epam.rd.entity.Book;
import com.epam.rd.exception.BookNotFoundException;
import com.epam.rd.repository.BookDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    @InjectMocks
    BookServiceImpl bookService;
    @Mock
    BookDao bookDao;
    @Mock
    ModelMapper modelMapper;
    BookDto bookDto;
    Book book;

    @Before
    public void setUp(){
        bookDto = new BookDto(1,"","","");
        book = new Book(1,"","","");
        when(modelMapper.map(bookDto , Book.class)).thenReturn(book);
        when(modelMapper.map(book , BookDto.class)).thenReturn(bookDto);
    }

    @Test
    @DisplayName("getAllBooks should return all Books")
    public void getAllBooksShouldReturnAllBooks(){
        when(bookDao.findAll()).thenReturn(List.of(book,book));
        Assertions.assertEquals(List.of(bookDto , bookDto) , bookService.getAllBooks());
    }

    @Test
    @DisplayName("getBook should return Book by Id")
    public void getBookShouldReturnBookById() throws BookNotFoundException {
        when(bookDao.findById(anyInt())).thenReturn(java.util.Optional.of(book));
        Assertions.assertEquals(bookDto, bookService.getBookById(1));
    }

    @Test
    @DisplayName("getBook should throw exception if Book not exist")
    public void getBookShouldThrowExceptionIfBookNotExist(){
        when(bookDao.findById(anyInt())).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(BookNotFoundException.class , ()->bookService.getBookById(1));
    }

    @Test
    @DisplayName("saveBook should save Book")
    public void saveBookShouldSaveBook(){
        Assertions.assertDoesNotThrow(()->bookService.saveBook(bookDto));
    }
}

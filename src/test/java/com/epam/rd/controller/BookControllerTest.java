package com.epam.rd.controller;

import com.epam.rd.service.BookService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {
    @MockBean
    BookService bookService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("getAllBooks should return all Book with ok status")
    public void getAllBooksShouldReturnAllBooksWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getBook should return Book by Id with Ok Status")
    public void getBookShouldReturnBookByIdWithOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

package com.example.tutorial3.controller;

import com.example.tutorial3.entity.Book;
import com.example.tutorial3.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "This endpoint returns manipulates all info about books")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    @ApiOperation(value = "Get all books")
    @GetMapping(value = "/all")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @ApiOperation(value = "Get a book by its id")
    @GetMapping(value = "/{id}")
    public Optional<Book> findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @ApiOperation(value = "Create a new book")
    @PostMapping
    public Book createUser(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @ApiOperation(value = "Updates book")
    @PutMapping
    public Book updateUser(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
}

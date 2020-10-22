package com.example.tutorial3.service;

import com.example.tutorial3.entity.Book;
import com.example.tutorial3.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> findBookById(Long id) {

        return repository.findById(id);
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Book book) {
        Optional<Book> byId = repository.findById(book.getId());
        byId.get().setName(book.getName());
        return repository.save(byId.get());
    }
}

package com.assist.lms.service;

import com.assist.lms.model.Author;
import com.assist.lms.model.Book;
import com.assist.lms.repository.AuthorRepository;
import com.assist.lms.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;

    public LibraryService(AuthorRepository authorRepo, BookRepository bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    public Author createAuthor(String name) {
        return authorRepo.save(new Author(name));
    }

    public List<Author> listAuthors() {
        return authorRepo.findAll();
    }

    public Book createBook(Long authorId, Book book) {
        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    public List<Book> listBooks() {
        return bookRepo.findAll();
    }

    public List<Book> listBooksByAuthor(Long authorId) {
        return bookRepo.findByAuthorId(authorId);
    }
}

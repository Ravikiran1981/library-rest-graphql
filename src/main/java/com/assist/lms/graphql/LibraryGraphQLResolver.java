package com.assist.lms.graphql;

import com.assist.lms.dto.AuthorDTO;
import com.assist.lms.dto.BookDTO;
import com.assist.lms.model.Book;
import com.assist.lms.model.Author;
import com.assist.lms.service.LibraryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LibraryGraphQLResolver {

    private final LibraryService libraryService;

    public LibraryGraphQLResolver(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @QueryMapping
    public List<AuthorDTO> authors() {
        return libraryService.listAuthors().stream()
                .map(a -> new AuthorDTO())
                .collect(Collectors.toList());
    }

    @QueryMapping
    public List<BookDTO> books(@Argument Long authorId) {
        List<Book> list = (authorId != null) ? libraryService.listBooksByAuthor(authorId)
                                            : libraryService.listBooks();
        return list.stream()
                   .map(b -> new BookDTO(b.getId(), b.getTitle(), b.getIsbn(), b.getAuthor().getName()))
                   .collect(Collectors.toList());
    }

    @MutationMapping
    public AuthorDTO createAuthor(@Argument String name) {
        Author a = libraryService.createAuthor(name);
        return new AuthorDTO();
    }

    @MutationMapping
    public BookDTO createBook(@Argument Long authorId,
                              @Argument String title,
                              @Argument String isbn) {
        Book b = libraryService.createBook(authorId, new Book(title, isbn, null));
        return new BookDTO(b.getId(), b.getTitle(), b.getIsbn(), b.getAuthor().getName());
    }
}

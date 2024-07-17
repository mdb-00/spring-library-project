package com.example.library.book;

import com.example.library.author.Author;
import com.example.library.author.AuthorRepository;
import com.example.library.genre.Genre;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    AuthorRepository authorRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("book with id " + bookId + " does not exists");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String title) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("book with id " + bookId + "does not exist"));

        if (title != null && !title.isEmpty() && !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }
    }

    public void assignAuthorToBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("book with id " + bookId + "does not exist"));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalStateException("author with id " + authorId + "does not exist"));
        book.assignAuthor(author);
        bookRepository.save(book);
    }
}

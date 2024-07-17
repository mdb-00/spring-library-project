package com.example.library.genre;

import com.example.library.book.Book;
import com.example.library.book.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    BookRepository bookRepository;

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public void addNewGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public void deleteGenre(Long genreId) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
            throw new IllegalStateException("genre with id " + genreId + " does not exists");
        }
        genreRepository.deleteById(genreId);
    }

    @Transactional
    public void updateGenre(Long genreId, String name) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new IllegalStateException("genre with id " + genreId + "does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(genre.getName(), name)) {
            genre.setName(name);
        }
    }

    public void assignBookToGenre(Long genreId, Long bookId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new IllegalStateException("genre with id " + genreId + "does not exist"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("book with id " + bookId + "does not exist"));
        genre.assignBook(book);
        genreRepository.save(genre);
    }
}

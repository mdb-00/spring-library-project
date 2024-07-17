package com.example.library.genre;

import com.example.library.book.Book;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Genre {
    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence"
    )
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "books_by_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> booksByGenres = new HashSet<>();

    public Genre() {}

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooksByGenres() {
        return booksByGenres;
    }

    public void assignBook(Book book) {
        booksByGenres.add(book);
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name='" + name + '\'';
    }
}

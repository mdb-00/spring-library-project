package com.example.library.author;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) { this.authorRepository = authorRepository; }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public void addNewAuthor(Author author) {
        authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
        boolean exists = authorRepository.existsById(authorId);
        if (!exists) {
            throw new IllegalStateException("author with id " + authorId + " does not exists");
        }
        authorRepository.deleteById(authorId);
    }

    @Transactional
    public void updateAuthor(Long authorId, String name) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalStateException("author with id " + authorId + "does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(author.getName(), name)) {
            author.setName(name);
        }
    }
}

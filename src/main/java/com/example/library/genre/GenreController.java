package com.example.library.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @PostMapping
    public void createNewGenre(@RequestBody Genre genre) {
        genreService.addNewGenre(genre);
    }

    @DeleteMapping(path = "{genreId}")
    public void deleteGenre(@PathVariable("genreId") Long genreId) {
        genreService.deleteGenre(genreId);
    }

    @PutMapping(path = "{genreId}")
    public void updateGenre(
            @PathVariable("genreId") Long genreId,
            @RequestParam(required = false) String name) {
        genreService.updateGenre(genreId, name);
    }

    @PutMapping("/{genreId}/books/{bookId}")
    Genre assignBookToGenre(
            @PathVariable Long genreId,
            @PathVariable Long bookId) {
        genreService.assignBookToGenre(genreId, bookId);
        return null;
    }
}

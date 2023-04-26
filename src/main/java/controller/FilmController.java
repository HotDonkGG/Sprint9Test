package controller;

import model.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
@Validated
public class FilmController {
    private final Logger log = LoggerFactory.getLogger(FilmController.class);
    private List<Film> films = new ArrayList<>();
    private int nextId = 1;

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        try {
            film.validate();
            film.setId(nextId++);
            films.add(film);
            log.info("Фильм добавлен с Айди " + film.getId());
        } catch (Exception e) {
            log.error("Ошибка добавления фильма " + e.getMessage());
            throw e;
        }
        return film;
    }

    @PutMapping
    public Film updateFilm(@PathVariable int id, @RequestBody Film film) {
        try {
            for (int i = 0; i < films.size(); i++) {
                if (films.get(i).getId() == id) {
                    film.validate();
                    films.set(i, film);
                    log.info("Фильм с Айди" + id + " обновлён");
                    return film;
                }
            }
            throw new Exception("Фильм с айди не был найден " + id);
        } catch (Exception e) {
            log.error("Ошибка обновления фильма " + e.getMessage());
        }
        return film;
    }

    @GetMapping
    public List<Film> getAllFilms() {
        try {
            if (!films.isEmpty()) {
                log.info("Получите Список фильмов" + films);
                return films;
            }
            throw new Exception("Мапа пустая не могу извлечь пользователей");
        } catch (Exception e) {
            log.error("Мапа пуста " + e.getMessage());
        }
        return films;
    }
}
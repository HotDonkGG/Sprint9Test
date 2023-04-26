package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    @PostMapping
    public User addUsers(@RequestBody User user) {
        try {
            user.validate();
            user.setId(nextId++);
            users.add(user);
            log.info("Добавлен новый пользователь" + user.getId());
        } catch (Exception e) {
            log.error("Ошибка добавления пользователя" + e.getMessage());
        }
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == id) {
                    user.validate();
                    users.set(i, user);
                    log.info("Обновление пользователя с айди" + id);
                    return user;
                }
            }
            throw new Exception("Пользователь не был найден по айди " + id);
        } catch (Exception e) {
            log.error("Ошибка обновления пользователя " + e.getMessage());
        }
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            if (!users.isEmpty()) {
                log.info("Получите список пользователей " + users);
                return users;
            }
            throw new Exception("Мапа пустая не могу извлечь пользователей");
        } catch (Exception e) {
            log.error("Мапа пуста " + e.getMessage());
        }
        return users;
    }
}
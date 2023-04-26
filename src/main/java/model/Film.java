package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import validationException.ValidationException;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private int id;
    private String name;
    private String description;
    private Date releaseDate;
    private Long duration;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void validate() {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("фильм не может быть пустым");
        } else if (description != null && description.length() > 200) {
            throw new ValidationException("Описание фильма более 200 символов");
        } else if (releaseDate != null && releaseDate.before(new Date(1895,12,28))) {
            throw new ValidationException("Дата релиза не может быть раньше чем 28/12/1895г.");
        } else if (duration != 0 && duration >= 0){
            throw new ValidationException("Фильм должен иметь положительное значение");
        }
    }
}
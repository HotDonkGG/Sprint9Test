package model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import validationException.ValidationException;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int id;
    String email;
    String login;
    String name;
    Date birthday;

    public int getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getLogin(){
        return login;
    }
    public String getName(){
        return name;
    }
    public Date getBirthday(){
        return birthday;
    }
    public void validate(){
        if (email == null || email.isEmpty() || !email.contains("@")){
            throw new ValidationException("Емейл юзера не может быть пустым и должен содержать @");
        } else if (login == null || login.contains(" ")) {
            throw new ValidationException("Логин пользователя не может быть пустым или содержать пробелы");
        } else if(birthday != null && birthday.after(new Date())){
            throw new ValidationException("Пользователь не может быть рождён в будущем");
        }
    }
}
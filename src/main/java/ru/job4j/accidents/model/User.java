package ru.job4j.accidents.model;

import java.util.Objects;

/**
 * User model
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
public class User {

    private int id;

    private String login;

    private String password;

    private String name;

    private String timezone;

    public User() {
    }

    public User(int id, String login, String password, String name, String timezone) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password)
                && Objects.equals(name, user.name) && Objects.equals(timezone, user.timezone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, timezone);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", name='" + name + '\''
                + ", timezone='" + timezone + '\''
                + '}';
    }

}
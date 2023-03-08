package ru.job4j.accidents.model;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;

/**
 * User model
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(builderMethodName = "of")
/*@Table(name = "users")*/
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;

    private String login;

    private String password;

    private String name;

    private String timezone;

}
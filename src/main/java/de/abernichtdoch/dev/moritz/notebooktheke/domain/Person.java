package de.abernichtdoch.dev.moritz.notebooktheke.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

//@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    public Person(@NotNull String name, @NotNull @Email String email) {
        this.name = name;
        this.email = email;
    }
}

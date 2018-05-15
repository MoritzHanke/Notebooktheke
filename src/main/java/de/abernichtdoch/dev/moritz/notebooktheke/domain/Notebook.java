package de.abernichtdoch.dev.moritz.notebooktheke.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notebook {

    @Id
    @NotNull
    private Long number;

    public Notebook(@NotNull Long number) {
        this.number = number;
    }
}

package de.abernichtdoch.dev.moritz.notebooktheke.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notebook {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long nummer;

    public void setNummer(Long nummer) {
        this.nummer = nummer;
    }

    public Long getId() {
        return id;
    }

    public Long getNummer() {
        return nummer;
    }
}

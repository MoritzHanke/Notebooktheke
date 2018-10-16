package de.abernichtdoch.dev.moritz.notebooktheke.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    private List<Notebook> notebooks;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Room room;
*/
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Person person;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime end;

}

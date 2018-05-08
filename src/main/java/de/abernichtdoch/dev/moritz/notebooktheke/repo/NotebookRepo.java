package de.abernichtdoch.dev.moritz.notebooktheke.repo;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotebookRepo extends CrudRepository<Notebook, Long> {

        Optional<Notebook> findByNummer(Long nummer);

}

package de.abernichtdoch.dev.moritz.notebooktheke.repo;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotebookRepo extends CrudRepository<Notebook, Long> {

}

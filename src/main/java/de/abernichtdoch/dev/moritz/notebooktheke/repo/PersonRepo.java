package de.abernichtdoch.dev.moritz.notebooktheke.repo;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends CrudRepository<Person, String> {

}

package de.abernichtdoch.dev.moritz.notebooktheke.repo;


import de.abernichtdoch.dev.moritz.notebooktheke.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends CrudRepository<Room, Long> {
}

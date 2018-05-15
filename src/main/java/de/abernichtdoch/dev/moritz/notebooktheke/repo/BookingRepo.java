package de.abernichtdoch.dev.moritz.notebooktheke.repo;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {

}

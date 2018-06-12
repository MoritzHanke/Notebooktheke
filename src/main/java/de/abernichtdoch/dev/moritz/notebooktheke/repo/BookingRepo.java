package de.abernichtdoch.dev.moritz.notebooktheke.repo;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {

    List<Booking> findByEndAfterAndStartBefore(LocalDateTime start, LocalDateTime end);

    List<Booking> findByEndGreaterThanEqualAndStartLessThanEqual(LocalDateTime start, LocalDateTime end);

}
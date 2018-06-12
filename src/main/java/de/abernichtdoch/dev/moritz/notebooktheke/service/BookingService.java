package de.abernichtdoch.dev.moritz.notebooktheke.service;

import com.google.common.collect.Lists;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.BookingRepo;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.NotebookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingService {


    @Autowired
    NotebookRepo notebookRepo;

    @Autowired
    BookingRepo bookingRepo;

    public Booking createBooking(List<Long> notebookIdList){

        List<Notebook> notebooks = notebookIdList.stream()
                .map(id -> notebookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Notebook existiert nicht: " + id)))
                .collect(Collectors.toList());

        Booking booking = new Booking();
        booking.setNotebooks(notebooks);
        booking.setStart(LocalDateTime.now());
        booking.setEnd(LocalDateTime.now().plusHours(2));
        bookingRepo.save(booking);

        return booking;
    }

    public Booking getBooking(Long id) {
        return bookingRepo.findById(id).orElseThrow( () -> new IllegalArgumentException("Buchung nicht gefunden: Id = " + id));
    }

    public List<Booking> getAllBookings() {
        return Lists.newArrayList(bookingRepo.findAll());
    }

    public List<Booking> getAllBookings( LocalDateTime start, LocalDateTime end) {
        return bookingRepo.findByEndAfterAndStartBefore(start, end);
    }




}

package de.abernichtdoch.dev.moritz.notebooktheke.service;

import com.google.common.collect.Lists;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Person;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.BookingRepo;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.NotebookRepo;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {


    @Autowired
    NotebookRepo notebookRepo;

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    PersonRepo personRepo;

    public Booking createBooking(List<Long> notebookIdList, LocalDateTime start, LocalDateTime end, String AccountEmail){

        List<Notebook> notebooks = notebookIdList.stream()
                .map(id -> notebookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Notebook existiert nicht: " + id)))
                .collect(Collectors.toList());

        List<String> emails = Arrays.asList("someperson@gmail.com", "blabla@gmx.de");
        String email = emails.get(new Random().nextInt() % emails.size());
        Person person = personRepo.findById(email).orElseThrow(() -> new IllegalArgumentException("Person existiert nicht: " + email));

        Booking booking = new Booking();
        booking.setNotebooks(notebooks);
        booking.setPerson(person);
        booking.setStart(start);
        booking.setEnd(end);
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

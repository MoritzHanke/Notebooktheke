package de.abernichtdoch.dev.moritz.notebooktheke.rest;


import com.fasterxml.jackson.annotation.JsonFormat;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import de.abernichtdoch.dev.moritz.notebooktheke.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BookingRestApi {

    @Autowired
    private BookingService service;

    @RequestMapping("/bookings")
    public List<Booking> getBooking() {
        return service.getAllBookings();
    }

    @RequestMapping("/bookings/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return service.getBooking(id);
    }

    @RequestMapping(value = "/bookings", method = RequestMethod.POST)
    public Booking createBooking(
            @RequestParam(value = "nb", required = true) List<Long> nb,
            @RequestParam(value = "start", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime start,
            @RequestParam(value = "end", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime end) {
        return service.createBooking(nb, start, end);
    }


}

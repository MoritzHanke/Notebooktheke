package de.abernichtdoch.dev.moritz.notebooktheke.rest;


import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import de.abernichtdoch.dev.moritz.notebooktheke.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingRestApi {

 @Autowired
    private BookingService service;

    @RequestMapping("/bookings/{id}")
    public Booking getBooking(@PathVariable Long id){
        return service.getBooking(id);
    }

    @RequestMapping("/bookings/create")
    public Booking createBooking(@RequestParam("nb") List<Long> nb){
        return service.createBooking(nb);
    }


}

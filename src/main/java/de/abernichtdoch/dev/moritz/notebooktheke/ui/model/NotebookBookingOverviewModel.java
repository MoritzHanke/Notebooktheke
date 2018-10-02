package de.abernichtdoch.dev.moritz.notebooktheke.ui.model;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotebookBookingOverviewModel {

    private List<Booking> bookings = new ArrayList<>();

    private Notebook notebook;

    public NotebookBookingOverviewModel(Notebook notebook) {
        this.notebook = notebook;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public boolean isBooked(LocalDateTime start, LocalDateTime end) {
        return bookings.stream()
                .anyMatch(booking -> booking.getEnd().isAfter(start) && booking.getStart().isBefore(end));
    }


    public Notebook getNotebook() {
        return notebook;
    }

    public int compare(NotebookBookingOverviewModel model){
        if (notebook.equals(model.notebook))
            return 0;

        return model.notebook.getNumber() > notebook.getNumber() ? -1 : 1;
    }
}

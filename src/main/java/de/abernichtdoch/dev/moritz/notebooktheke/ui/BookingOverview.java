package de.abernichtdoch.dev.moritz.notebooktheke.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Booking;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.service.BookingService;
import de.abernichtdoch.dev.moritz.notebooktheke.ui.model.NotebookBookingOverviewModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@SpringUI(path = "/overview")
public class BookingOverview extends UI {

    @Autowired
    private BookingService bookingService;

    //private final Binder<AdvertiserMapping> binder = new Binder<>(AdvertiserMapping.class);

    private final Grid<NotebookBookingOverviewModel> grid = new Grid<>(NotebookBookingOverviewModel.class);
    /*
    private final Label title = new Label("AWIN Advertiser IDs mapping");
    private final TextField awinAdvertiserId = new TextField("AWIN Advertiser ID");
    private final TextField paybackAdvertiserId = new TextField("Country Specific Advertiser ID");
    private final Button save = new Button("Save", this::saveAdvertiserMapping);
    private final Button delete = new Button("Delete", this::deleteAdvertiserMapping);
     */

    @Override
    protected void init(VaadinRequest request) {

        grid.setItems(loadBookings().values());

        grid.addColumn(model -> model.getNotebook().getNumber()).setCaption("Notebook");

        for (int hour = 8; hour < 16; hour++) {
            for (int min = 0; min < 60; min += 5) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), hour, min);
                LocalDateTime end = start.plusMinutes(5);
                grid.addColumn(nb -> nb.isBooked(start, end) ? "x" : "").setCaption("" + hour + ":" + min);
            }
        }

        VerticalLayout layout = new VerticalLayout(grid);
        setContent(layout);
    }

    private Map<Notebook, NotebookBookingOverviewModel> loadBookings() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 8, 0);
        LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 16, 0);
        List<Booking> bookings = bookingService.getAllBookings(start, end);

        Map<Notebook, NotebookBookingOverviewModel> models = new HashMap<>();
        bookings.forEach(booking ->
                booking.getNotebooks().forEach(nb -> {
                    NotebookBookingOverviewModel model = models.computeIfAbsent(nb, k -> new NotebookBookingOverviewModel(k));
                    model.addBooking(booking);
                })

        );

        return models;

    }

    /*
    @Override
    protected void init(VaadinRequest request) {
        updateGrid();
        grid.setColumns("advertiserId", "paybackAdvertiserId");
        grid.addSelectionListener(e -> updateForm());

        binder.forField(awinAdvertiserId).bind(AdvertiserMapping::getAwinAdvertiserId, AdvertiserMapping::setAwinAdvertiserId);
        binder.forField(paybackAdvertiserId).bind(AdvertiserMapping::getPaybackAdvertiserId, AdvertiserMapping::setPaybackAdvertiserId);
        HorizontalLayout inputs = new HorizontalLayout(awinAdvertiserId, paybackAdvertiserId);
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        VerticalLayout layout = new VerticalLayout(title, grid, inputs, buttons);
        setContent(layout);
        updateForm();
    }

    private void updateGrid() {
        List<AdvertiserMapping> mappings = advertiserMappingService.findAll();
        grid.setItems(mappings);
    }

    private void updateForm() {
        if (grid.asSingleSelect().isEmpty()) {
            save.setCaption("Create");
            advertiserMapping = new AdvertiserMapping(0, "");
            binder.setBean(advertiserMapping);
        } else {
            save.setCaption("Update");
            advertiserMapping = grid.asSingleSelect().getValue();
            binder.setBean(advertiserMapping);
        }
    }

    private void saveAdvertiserMapping(Button.ClickEvent e) {
        advertiserMappingService.update(advertiserMapping);
        updateGrid();
    }

    private void deleteAdvertiserMapping(Button.ClickEvent e) {
        advertiserMappingService.delete(advertiserMapping.getAdvertiserId());
        advertiserMapping = new AdvertiserMapping(0, "");
        updateGrid();
    }
    */
}

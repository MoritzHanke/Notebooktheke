package de.abernichtdoch.dev.moritz.notebooktheke.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.DateField;
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

    public static final int FROM_HOUR = 8;
    public static final int TO_HOUR = 16;
    @Autowired
    private BookingService bookingService;

    //private final Binder<AdvertiserMapping> binder = new Binder<>(AdvertiserMapping.class);

    private final Grid<NotebookBookingOverviewModel> grid = new Grid<>(NotebookBookingOverviewModel.class);

    private final DateField dateField = new DateField();

    /*
    private final Label title = new Label("AWIN Advertiser IDs mapping");
    private final TextField awinAdvertiserId = new TextField("AWIN Advertiser ID");
    private final TextField paybackAdvertiserId = new TextField("Country Specific Advertiser ID");
    private final Button save = new Button("Save", this::saveAdvertiserMapping);
    private final Button delete = new Button("Delete", this::deleteAdvertiserMapping);
     */

    @Override
    protected void init(VaadinRequest request) {

        LocalDateTime day = LocalDateTime.of(2018, 10,2, 0,0);

        Map<Notebook, NotebookBookingOverviewModel> models = loadBookings(day, FROM_HOUR, TO_HOUR);
        grid.removeAllColumns();
        grid.setItems(models.values().stream().sorted(NotebookBookingOverviewModel::compare));

        grid.addColumn(model -> model.getNotebook().getNumber()).setCaption("Notebook");

        for (int hour = FROM_HOUR; hour < TO_HOUR; hour++) {
            LocalDateTime start = LocalDateTime.of(day.getYear(), day.getMonth(), day.getDayOfMonth(), hour, 0);
            LocalDateTime end = start.plusMinutes(60);
            //grid.addColumn(nbModel -> nbModel.isBooked(start, end) ? "x" : "").setCaption("" + hour + "-" + (hour + 1));
            grid.addColumn(nbModel -> nbModel.getBooking(start, end).map(booking -> booking.getPerson().getName()).orElse("")).setCaption("" + hour + "-" + (hour + 1));
        }
        VerticalLayout layout = new VerticalLayout(grid);
        setContent(layout);
    }

    private Map<Notebook, NotebookBookingOverviewModel> loadBookings(LocalDateTime day, int fromHour, int toHour) {
        LocalDateTime start = LocalDateTime.of(day.getYear(), day.getMonth(), day.getDayOfMonth(), fromHour, 0);
        LocalDateTime end = LocalDateTime.of(day.getYear(), day.getMonth(), day.getDayOfMonth(), toHour, 0);
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

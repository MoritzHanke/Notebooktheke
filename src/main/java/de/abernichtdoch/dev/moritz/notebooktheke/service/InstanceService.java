package de.abernichtdoch.dev.moritz.notebooktheke.service;

import com.google.common.collect.Lists;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Room;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.NotebookRepo;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceService {

    public static final int FLOOR_MARKER = 100;

    @Autowired
    NotebookRepo notebookRepo;

    @Autowired
    RoomRepo roomRepo;

    //--------- ROOMS -----------------------------

    public Room createRoom(Long room) {

        //todo create validator
        long roomNumber = room % FLOOR_MARKER;
        long floor = room / FLOOR_MARKER;
        if (floor > 3 || roomNumber > 18 || roomNumber < 1) {
            throw new IllegalArgumentException("Invalide Raumnummer " + room);
        }

        Room aRoom = new Room(room, String.format("R.%03d", room));
        roomRepo.save(aRoom);
        return aRoom;
    }

    public Room getRoom(Long Room) {
        return roomRepo.findById(Room).orElseThrow(() -> new IllegalArgumentException("Der Raum " + Room + " konnte nicht gefunden werden!"));
    }

    public List<Room> getAllRooms() {
        return Lists.newArrayList(roomRepo.findAll());
    }

    public void deleteRoom(Long room) {
        roomRepo.deleteById(room);
    }

    //--------- NOTEBOOKS -----------------------------

    public Notebook createNotebook(Long number) {
        Notebook neuesNotebook = new Notebook(number);
        notebookRepo.save(neuesNotebook);
        return neuesNotebook;
    }

    public Notebook getNotebook(Long number) {
        return notebookRepo.findById(number)
                .orElseThrow(() -> new IllegalArgumentException("Notebook konnte nicht gefunden werden: Id = " + number));
    }


    public List<Notebook> getAllNotebooks() {
        return Lists.newArrayList(notebookRepo.findAll());
    }

    //todo delete()

    //todo PERSON

}

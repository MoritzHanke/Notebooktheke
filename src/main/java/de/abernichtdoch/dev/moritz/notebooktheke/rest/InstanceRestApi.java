package de.abernichtdoch.dev.moritz.notebooktheke.rest;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Person;
import de.abernichtdoch.dev.moritz.notebooktheke.domain.Room;
import de.abernichtdoch.dev.moritz.notebooktheke.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstanceRestApi {

    @Autowired
    private InstanceService service;

    //--------- ROOMS -----------------------------

    @RequestMapping("/rooms")
    public List<Room> getAllRooms(){
        return service.getAllRooms();
    }

    @RequestMapping("/rooms/{room}")
    public Room get(@PathVariable Long room){
    return service.getRoom(room);
    }

    @RequestMapping(value= "/rooms/{room}", method = RequestMethod.POST)
    public Room createRoom(@PathVariable Long room){
        return service.createRoom(room);
    }

    @RequestMapping(value= "/rooms/{room}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable Long room){
        service.deleteRoom(room);
    }

    //--------- NOTEBOOKS -----------------------------

    @RequestMapping("/notebooks")
    public List<Notebook> getallNotebooks(){
        return service.getAllNotebooks();
    }

    @RequestMapping("/notebooks/{number}")
    public Notebook getNotebook(@PathVariable Long number){
        return service.getNotebook(number);
    }

    @RequestMapping(value ="/notebooks/{number}", method = RequestMethod.POST)
    public Notebook createNotebook(@PathVariable Long number){
        return service.createNotebook(number);
    }

    //----------------- Persons --------------------------

    @RequestMapping("/persons")
    public List<Person> getallPersons(){
        return service.getAllpersons();
    }

    @RequestMapping("/persons/{email}")
    public Person getPerson(@PathVariable String email){
        return service.getPerson(email);
    }

    @RequestMapping(value ="/persons/{email}/{name}", method = RequestMethod.POST)
    public Person createPerson(@PathVariable String email, @PathVariable String name){
        return service.createPerson(email, name);
    }

    //todo delete()

}

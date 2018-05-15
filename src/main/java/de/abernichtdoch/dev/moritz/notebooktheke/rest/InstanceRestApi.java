package de.abernichtdoch.dev.moritz.notebooktheke.rest;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstanceRestApi {

    @Autowired
    private InstanceService service;

    @RequestMapping("/notebooks/{number}")
    public Notebook get(@PathVariable Long number){
        return service.getNotebook(number);
    }

    @RequestMapping("/notebooks/{number}/create")
    public Notebook create(@PathVariable Long number){

        return service.createNotebook(number);
    }

    @RequestMapping("/notebooks")
    public List<Notebook> getall(){
        return service.getAllNotebooks();
    }
}

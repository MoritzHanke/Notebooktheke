package de.abernichtdoch.dev.moritz.notebooktheke.rest;

import de.abernichtdoch.dev.moritz.notebooktheke.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstanceRestApi {

    @Autowired
    private InstanceService service;

    @RequestMapping("/notebooks/{number}")
    public String get(@PathVariable Long number){
        return service.getNotebook(number);
    }

    @RequestMapping("/notebooks/{number}/create")
    public String create(@PathVariable Long number){

        return service.createNotebook(number);
    }


}

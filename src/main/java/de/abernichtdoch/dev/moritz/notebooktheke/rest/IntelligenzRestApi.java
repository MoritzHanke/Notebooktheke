package de.abernichtdoch.dev.moritz.notebooktheke.rest;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.IntelligenzAntwort;
import de.abernichtdoch.dev.moritz.notebooktheke.service.IntelligencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntelligenzRestApi {

    @Autowired
    IntelligencyService service;

    //localhost:8080/names/moritz/sayhello
    @RequestMapping("/names/{name}/sayhello")
    public IntelligenzAntwort greeting(@PathVariable String name) {

        String hello = service.sayHello(name);
        String intell = service.getIntelligenzFaktor(name);

        return new IntelligenzAntwort(hello, intell);
    }

}

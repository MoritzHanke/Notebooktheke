package de.abernichtdoch.dev.moritz.notebooktheke.service;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.NotebookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntelligencyService {

    @Autowired
    NotebookRepo repo;

    public String createNotebook(Long nummer) {

        Notebook neuesNotebook = new Notebook();
        neuesNotebook.setNummer(nummer);
        Notebook nb = repo.save(neuesNotebook);

        return "Notebook " + nummer + " erzeugt mit id = " + nb.getId();
    }

    //-------------------

    String name;


    public String sayHello(String name){
        this.name = name; return "Hello " + name;
    }

    public String getIntelligenzFaktor(String name){
        return  name + " ist " + (name.equalsIgnoreCase("Moritz") ? "sehr schlau" : "dumm");
    }


}

package de.abernichtdoch.dev.moritz.notebooktheke.service;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.NotebookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstanceService {

    @Autowired
    NotebookRepo repo;

    public Notebook createNotebook(Long number) {
        //FehlerQuelle  #1
        //return String.valueOf(number);

        Notebook neuesNotebook = new Notebook(number);
        repo.save(neuesNotebook);
        return neuesNotebook;
    }

    public Notebook getNotebook(Long number) {
        //FehlerQuelle #1
        //return String.valueOf(number);
        return repo.findById(number)
                .orElseThrow(() -> new IllegalArgumentException("Notebook konnte nicht gefunden werden: Id = " + number));
    }


    public List<Notebook> getAllNotebooks(){
        List<Notebook> nbs = new ArrayList<>();
        repo.findAll().forEach(nbs::add);
        return nbs;
    }


}

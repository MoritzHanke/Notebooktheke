package de.abernichtdoch.dev.moritz.notebooktheke.service;

import de.abernichtdoch.dev.moritz.notebooktheke.domain.Notebook;
import de.abernichtdoch.dev.moritz.notebooktheke.repo.NotebookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceService {

    @Autowired
    NotebookRepo repo;

    public String createNotebook(Long number) {
        //FehlerQuelle  #1
        //return String.valueOf(number);

        Notebook neuesNotebook = new Notebook(number);
        repo.save(neuesNotebook);
        return "Notebook " + number + " erzeugt mit nr = " + neuesNotebook.getNumber();
    }

    public String getNotebook(Long number) {
        //FehlerQuelle #1
        //return String.valueOf(number);
        return repo.findById(number).map(n -> "Gefunden: " + n.getNumber()).orElse("Notebook " + number + " nicht gefunden!");
    }


}

package de.abernichtdoch.dev.moritz.notebooktheke.service;

import org.springframework.stereotype.Service;

@Service
public class IntelligencyService {



    public String sayHello(String name){
        this.name = name; return "Hello " + name;
    }

    public String getIntelligenzFaktor(String name){
        return  name + " ist " + (name.equalsIgnoreCase("Moritz") ? "sehr schlau" : "dumm");
    }


















































    String name;

}

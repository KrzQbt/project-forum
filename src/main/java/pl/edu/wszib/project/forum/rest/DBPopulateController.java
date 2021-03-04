package pl.edu.wszib.project.forum.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.project.forum.services.IDBPopulateService;
import pl.edu.wszib.project.forum.session.SessionObject;

import javax.annotation.Resource;

@RestController
public class DBPopulateController {

    @Autowired
    IDBPopulateService dbPopulateService;


    @GetMapping("/db")
    public void dbPopulate(){
        dbPopulateService.dbPopulate();
    }
}

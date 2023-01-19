package com.tamkstudents.cookbook.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiController extends AbstractController{

    @GetMapping
    public @ResponseBody ResponseEntity<String> test(){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

}

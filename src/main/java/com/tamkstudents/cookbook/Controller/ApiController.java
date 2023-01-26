package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Service.RecipeService;
import com.tamkstudents.cookbook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController extends AbstractController{

    @Autowired
    UserService userService;

    @Autowired
    RecipeService recipeService;

    @GetMapping
    public @ResponseBody ResponseEntity<String> test(){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    @GetMapping("/users")
    public @ResponseBody ResponseEntity<List<UserDto>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }
}

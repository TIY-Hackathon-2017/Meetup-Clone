package com.tiyhackathon.meetup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David Turk on 8/8/17.
 */
@RestController
public class MainController {

    @GetMapping("/")
    public String index(){
        return "You made it!";
    }
}

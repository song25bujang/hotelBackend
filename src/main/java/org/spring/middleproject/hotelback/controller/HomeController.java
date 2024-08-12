package org.spring.middleproject.hotelback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(){
        String data = "HOTEL RESERVATION";
        System.out.println(data);
        return data;
    }
}

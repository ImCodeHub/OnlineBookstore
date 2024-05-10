package com.book.OnlineBookstore.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api")
public class BookController {
    @GetMapping("welcom")
    public String welcome() {
        return "welcome to the spring boot";
    }   
}

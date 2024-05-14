package com.book.OnlineBookstore.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.book.OnlineBookstore.Model.AddBook;
import com.book.OnlineBookstore.ServiceImpl.BookServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @GetMapping("path")
    public String postMethodName() {
        return "all is well";
    }
    

    @PostMapping("/addbook")
    public ResponseEntity<String> addBookDetails(@RequestParam("image") MultipartFile file, @RequestBody AddBook addBook) {
        String response = bookServiceImpl.addBook(file, addBook);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

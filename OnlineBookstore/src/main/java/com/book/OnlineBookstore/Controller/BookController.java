package com.book.OnlineBookstore.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.book.OnlineBookstore.Entity.Book;
import com.book.OnlineBookstore.Model.AddBook;
import com.book.OnlineBookstore.ServiceImpl.BookServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    // To add book
    @PostMapping("/addbook")
    public ResponseEntity<String> createBook(@RequestPart("book") Book addBook, @RequestPart("image") MultipartFile imageFile) {
        try {
            String saveBook = bookServiceImpl.addBook(addBook, imageFile);
            return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("somthing went wrong while saving book details!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // To Retrive all book in list
    @GetMapping("/books")
    public ResponseEntity<List<AddBook>> BookList() {
        List<AddBook> books = bookServiceImpl.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Retrive the single book details
    @GetMapping("/book/{id}")
    public ResponseEntity<List<AddBook>> getBookById(@PathVariable Long id) {
        List<AddBook> book = bookServiceImpl.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateBook/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody AddBook addBook) {
        try {
            String response = bookServiceImpl.updateBookDetails(id, addBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Long id) {

        Boolean response = bookServiceImpl.deleteBook(id);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}

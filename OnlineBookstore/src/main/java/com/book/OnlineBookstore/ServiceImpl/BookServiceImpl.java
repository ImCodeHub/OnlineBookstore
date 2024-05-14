package com.book.OnlineBookstore.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.book.OnlineBookstore.Entity.Book;
import com.book.OnlineBookstore.Model.AddBook;
import com.book.OnlineBookstore.Repository.BookRepository;
import com.book.OnlineBookstore.Service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public String addBook(MultipartFile file, AddBook addBook) {

        try {
            byte[] imageData = file.getBytes();
            
            Book book = new Book();
            book.setImage(imageData);
            book.setTitle(addBook.getTitle());
            book.setAuthor(addBook.getAuthor());
            book.setPublicationYear(addBook.getPublicationYear());
            book.setIsbn(addBook.getIsbn());
            book.setGenre(addBook.getGenre());
            book.setPrice(addBook.getPrice());

            bookRepository.save(book);

            return "Book added successfully!";


        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add book:"+e.getMessage();
        }
    }

}

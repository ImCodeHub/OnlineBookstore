package com.book.OnlineBookstore.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.OnlineBookstore.Entity.Book;
import com.book.OnlineBookstore.Model.AddBook;
import com.book.OnlineBookstore.Repository.BookRepository;
import com.book.OnlineBookstore.Service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public String addBook(AddBook addBook) {
        try {
            Book book = new Book(addBook.getTitle(), addBook.getAuthor(), addBook.getPublicationYear(),
                    addBook.getIsbn(), addBook.getGenre(), addBook.getPrice(), addBook.getImage());
            bookRepository.save(book);
            return "book details saved successfully!";
        } catch (Exception e) {
            return "something went wrong: " + e.getMessage();
        }
    }

    @Override
    public List<AddBook> getBooks() {
        List<AddBook> response = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            AddBook addBook = new AddBook();
            addBook.setTitle(book.getTitle());
            addBook.setAuthor(book.getAuthor());
            addBook.setPublicationYear(book.getPublicationYear());
            addBook.setIsbn(book.getIsbn());
            addBook.setGenre(book.getGenre());
            addBook.setPrice(book.getPrice());
            addBook.setImage(book.getImage());

            response.add(addBook);
        }
        return response;
    }

    @Override
    public List<AddBook> getBookById(Long id) {
        List<AddBook> response = new ArrayList<>();
        Optional<Book> optional = bookRepository.findById(id);

        if(optional.isPresent()){
            Book books = optional.get();
            AddBook addBook = new AddBook();
            addBook.setTitle(books.getTitle());
            addBook.setAuthor(books.getAuthor());
            addBook.setPublicationYear(books.getPublicationYear());
            addBook.setIsbn(books.getIsbn());
            addBook.setGenre(books.getGenre());
            addBook.setPrice(books.getPrice());
            addBook.setImage(books.getImage());

            response.add(addBook);
        }else{
            return null;
        }
        return response;
    }

    @Override
    public String updateBookDetails(Long id, AddBook updateBook) {
        Optional<Book> optional = bookRepository.findById(id);
        Book book = optional.get();

        if(!optional.isPresent()){
            return "book details not found.";
        }

        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setPublicationYear(updateBook.getPublicationYear());
        book.setIsbn(updateBook.getIsbn());
        book.setGenre(updateBook.getGenre());
        book.setPrice(updateBook.getPrice());
        book.setImage(updateBook.getImage());

        bookRepository.save(book);

        return "book details updated successfully!";
        
    }

    @Override
    public Boolean deleteBook(Long id) {
        Optional<Book> optiion = bookRepository.findById(id);
        if(optiion.isPresent()){
            bookRepository.deleteById(id);
            return true;

        }else{
            return false;
        }

    }
}
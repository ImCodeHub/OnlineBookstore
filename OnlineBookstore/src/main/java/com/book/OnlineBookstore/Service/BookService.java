package com.book.OnlineBookstore.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.book.OnlineBookstore.Entity.Book;
import com.book.OnlineBookstore.Model.AddBook;

public interface BookService {
    public String addBook(Book addBook, MultipartFile imagFile) throws IOException;
    public List<AddBook> getBooks();
    public List<AddBook> getBookById(Long id);
    public String updateBookDetails(Long id, AddBook addBook);
    public Boolean deleteBook(Long id);
}

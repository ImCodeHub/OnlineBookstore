package com.book.OnlineBookstore.Service;

import java.util.List;

import com.book.OnlineBookstore.Model.AddBook;

public interface BookService {
    public String addBook(AddBook addbook);
    public List<AddBook> getBooks();
    public List<AddBook> getBookById(Long id);
    public String updateBookDetails(Long id, AddBook addBook);
    public Boolean deleteBook(Long id);
}

package com.book.OnlineBookstore.Service;

import org.springframework.web.multipart.MultipartFile;

import com.book.OnlineBookstore.Model.AddBook;

public interface BookService {
    public String addBook(MultipartFile file, AddBook book);
}

package com.book.OnlineBookstore.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBook {
    private byte[] image; 

    private String title;

    private String author;

    private int publicationYear;

    private String isbn;

    private String genre;

    private int price;

}

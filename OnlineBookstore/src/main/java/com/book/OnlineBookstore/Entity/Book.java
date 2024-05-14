package com.book.OnlineBookstore.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] image;

    private String title;

    private String author;

    @Column(name = "publication_year")
    private int publicationYear;

    private String isbn;

    private String genre;

    private int price;

    public Book(byte[] image, String title, String author, int publicationYear, String isbn, String genre,
            int price) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.genre = genre;
        this.price = price;
    }

}

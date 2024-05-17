package com.book.OnlineBookstore.ServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.book.OnlineBookstore.Entity.Book;
import com.book.OnlineBookstore.Model.AddBook;
import com.book.OnlineBookstore.Repository.BookRepository;
import com.book.OnlineBookstore.Service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    BookRepository bookRepository;

    // Method to save image in directory.
    public String saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path path = Paths.get(uploadDir, imageFileName);
            Files.createDirectories(path.getParent());
            Files.write(path, imageFile.getBytes());
            return imageFileName;
        }
        return "image file not found.";
    }

    // created saprate method to get encoded image from directory. 
    public String getEncodedImageFromDirectory(String imageFile){
        Path imagePath = Paths.get(uploadDir).resolve(imageFile);
            try {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
                return "data:image/jpeg;base64," + encodedImage;
            } catch (IOException e) {

                return null;
            }
    }

    @Override
    public byte[] getImageById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        Path imagePath = Paths.get(uploadDir).resolve(book.getImage());
        try {
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("could not read file" + e.getMessage(), e);
        }
    }

    @Override
    public String addBook(Book addBook, MultipartFile imageFile) throws IOException {
        if (addBook != null) {
            addBook.setImage(saveImage(imageFile));
            bookRepository.save(addBook);
            return "book details saved successfully!";
        } else {
            return "image file is empty.";
        }
    }

    @Override
    public List<AddBook> getAllBooks() {
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

            // Retrive and encode the image file.
            addBook.setImage(getEncodedImageFromDirectory(book.getImage()));
           
            response.add(addBook);
        }
        return response;
    }

    @Override
    public List<AddBook> getBookById(Long id) {
        List<AddBook> response = new ArrayList<>();
        Optional<Book> optional = bookRepository.findById(id);

        if (optional.isPresent()) {
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
        } else {
            return null;
        }
        return response;
    }

    @Override
    public String updateBookDetails(Long id, AddBook updateBook) {
        Optional<Book> optional = bookRepository.findById(id);
        Book book = optional.get();

        if (!optional.isPresent()) {
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
        if (optiion.isPresent()) {
            bookRepository.deleteById(id);
            return true;

        } else {
            return false;
        }

    }

}
package edu.monmouth.book;

import java.util.Objects;

public class Book {
    private String title;
    private int numberOfPages;
    private BookTypes type; 
    private double price; 
    
    public Book(int numberOfPages, double price, String title, BookTypes type) throws BookException {
        if (title == null || title.isEmpty()) {
            throw new BookException("Title cannot be null or empty");
        }
        if (numberOfPages <= 0) {
            throw new BookException("Number of pages must be positive");
        }
        if (price < 0) {
            throw new BookException("Price cannot be negative");
        }
        if (type == null) {
            throw new BookException("Type cannot be null");
        }
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.type = type;
        this.price = price;
    }
    

    @Override
    public boolean equals(Object o) {
        System.out.println("Checking equals...");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        // Equality is checked based on price, title, numberOfPages, and type
        return numberOfPages == book.numberOfPages &&
                Double.compare(book.price,price)==0 &&
        		Objects.equals(title, book.title) &&
                type == book.type;  
    }

    @Override
    public int hashCode() {
        System.out.println("Invoking hashCode...");
        // Hashcode is computed based on title, numberOfPages, type, price
        return Objects.hash(title, numberOfPages, type, price);
    }

    // toString method for printing details of the book
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", type=" + type +
                "price="+price+
                '}';
    }
}

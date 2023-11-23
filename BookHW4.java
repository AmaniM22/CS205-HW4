package edu.monmouth.book;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class BookHW4 {
    public static void main(String args[]) {
        // Validate that 2 command line arguments are passed in
        if (args.length != 2) {
            System.err.println("Please provide exactly 2 command line arguments");
            return;
        }

        // First argument is the name of the data file
        String dataFileName = args[0];
        // Second argument is the name of the file to which stdout & stderr are redirected
        String outputFileName = args[1];

        // Redirect stdout & stderr to specified file
        try {
            System.setOut(new PrintStream(new FileOutputStream(outputFileName)));
            System.setErr(new PrintStream(new FileOutputStream(outputFileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Create a HashSet that contains Book objects (name it bookSet)
        Set<Book> bookSet = new HashSet<>();

        // Open and read the data file, creating Book objects (if valid)
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String title = parts[0];
                BookTypes type = BookTypes.valueOf(parts[1].toUpperCase());
                int numberOfPages = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);

                try {
                    Book book = new Book(numberOfPages, price, title, type);
                    if (bookSet.add(book)) {
                        System.out.println("Successfully added book to HashSet: " + book);
                    } else {
                        System.out.println("Book was not added to HashSet (duplicate): " + book);
                    }
                } catch (BookException e) {
                    System.err.println("Cannot create a Book object from these values:\n" + line + "\n" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot read data file: " + e.getMessage());
            return;
        }

        // After data file is read and valid Book objects added to HashSet iterate over HashSet
        // Printing each Book object
        for (Book book : bookSet) {
            System.out.println(book.toString());
        }
    
        // Determine if the following 2 books are in the HashSet
        try {
            checkBook(bookSet, new Book(829, 23.95, "The Soloman Curse", BookTypes.HARDBACK));
            checkBook(bookSet, new Book(629, 87.00, "The Big Bang Theory", BookTypes.HARDBACK));
        } catch (BookException e) {
            System.err.println("Cannot create a Book object from these values:\n" + e.getMessage());
        }}
    // Helper method to check if a book is in the HashSet
    private static void checkBook(Set<Book> bookSet, Book book) {
        if (bookSet.contains(book)) {
            System.out.println(book + "\nExists in bookSet");
        } else {
            System.out.println(book + "\nDoes not exist in bookSet");
        }
    }
}
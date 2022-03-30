package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "libraryBook")
public class LibraryBook {
    @XmlElement(required = true)
    private Book book;
    @XmlElement(name="publishedBooks", required = true)
    private List<PublishedBook> publishedBooks = new ArrayList<>();

    public LibraryBook(){}

    public LibraryBook(Book book, List<PublishedBook> publishedBooks) {
        this.book = book;
        this.publishedBooks = publishedBooks;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<PublishedBook> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<PublishedBook> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    public void addToList(PublishedBook publishedBook) {
        publishedBooks.add(publishedBook);
    }

    @Override
    public String toString() {
        return "LibraryBook{" +
                "book=" + book +
                ", publishedBooks=" + publishedBooks +
                '}';
    }
}

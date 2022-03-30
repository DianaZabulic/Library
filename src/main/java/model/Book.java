package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "book")
public class Book {
    @XmlElement(required = true)
    private String title;
    @XmlElement(required = true)
    private String author;
    @XmlElement(required = true)
    private String domain;

    public Book(){}

    public Book(String title, String author, String domain) {
        this.title = title;
        this.author = author;
        this.domain = domain;
    }

    public Book(Book book) {
        this.title = book.title;
        this.author = book.author;
        this.domain = book.domain;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", domain=" + domain +
                '}';
    }
}
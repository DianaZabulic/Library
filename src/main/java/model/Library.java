package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "library")
public class Library {
    @XmlElement(name = "id", required = true)
    private int ID;
    @XmlElement(required = true)
    private String name;
    @XmlElement(name = "librarybooks", required = true)
    private List<LibraryBook> libraryBooks = new ArrayList<>();

    public Library() {
    }

    public Library(int ID, String name, List<LibraryBook> libraryBooks) {
        this.ID = ID;
        this.name = name;
        this.libraryBooks = libraryBooks;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LibraryBook> getLibraryBooks() {
        return libraryBooks;
    }

    public void setLibraryBooks(List<LibraryBook> libraryBooks) {
        this.libraryBooks = libraryBooks;
    }

    public void addToList(LibraryBook libraryBook) {
        libraryBooks.add(libraryBook);
    }
}

package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "published book")
public class PublishedBook {
    @XmlElement(name = "id", required = true)
    private final long ID;
    @XmlElement(required = true)
    private int isbn;
    @XmlElement(required = true)
    private String publisher;
    @XmlElement(name = "publicationyear", required = true)
    private int publicationYear;

    public PublishedBook(){
        ID = -1;
    }

    public PublishedBook(long ID, int isbn, String publisher, int publicationYear) {
        this.ID = ID;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;

    }

    public long getID() {
        return ID;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "PublishedBook{" +
                "ID=" + ID +
                ", isbn=" + isbn +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}

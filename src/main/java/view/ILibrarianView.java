package view;

import model.Book;
import model.PublishedBook;

import java.util.List;

public interface ILibrarianView extends ISubscriberView{
    ////////operatii pt subscriber
    String getCNP();
    String getName();
    String getSubscriberID();
    ///////operatii pt carte
    String getTitle();
    String getAuthor();
    String getDomain();
    String getYear();
    String getPublisher();
    String getISBN();
    String getID();
    String getBookID();
    List<Book> getBooks();
    List<PublishedBook> getPublishedBooks();
}

package view;

import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Book;
import model.LibraryBook;
import model.PublishedBook;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface ISubscriberView {
    TableView<Book> getBooksTable();

    long getLibraryID();

    String getUsername();

    String getPassword();

    String getSearchTitle();

    String getFilterType();

    void setFilterType(String filterType);

    TableView<PublishedBook> getPublishedBooksTable();

    TableColumn<Book, String> getTitleColumn();

    TableColumn<Book, String> getAuthorColumn();

    TableColumn<Book, String> getDomainColumn();

    TableColumn<PublishedBook, Long> getIdColumn();

    TableColumn<PublishedBook, Integer> getIsbnColumn();

    TableColumn<PublishedBook, String> getPublisherColumn();

    TableColumn<PublishedBook, Integer> getPublicationYearColumn();

    MenuButton getFilterButton();

    String getFilter();

    List<LibraryBook> getFilteredBooks() throws JAXBException;
}

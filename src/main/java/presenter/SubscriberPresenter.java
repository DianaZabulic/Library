package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import view.ISubscriberView;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberPresenter {
    ISubscriberView iSubscriberView;
    PersistenceLibrary persistenceLibrary;
    PersistenceUser persistenceUser;
    PersistenceLoan persistenceLoan;

    public SubscriberPresenter(ISubscriberView iSubscriberView) {
        this.iSubscriberView = iSubscriberView;
        persistenceUser = new PersistenceUser();
        persistenceLibrary = new PersistenceLibrary();
        persistenceLoan = new PersistenceLoan();
    }

    public List<Book> getBooks(List<LibraryBook> libraryBooks) {
        List<Book> books = new ArrayList<>();
        for (LibraryBook libraryBook : libraryBooks) {
            Book book = libraryBook.getBook();
            for (int i = 0; i < libraryBook.getPublishedBooks().size(); i++) {
                books.add(book);
            }
        }
        return books;
    }

    public List<PublishedBook> getPublishedBooks(List<LibraryBook> libraryBooks) {
        List<PublishedBook> publishedBooks = new ArrayList<>();
        for (LibraryBook libraryBook : libraryBooks) {
            publishedBooks.addAll(libraryBook.getPublishedBooks());
        }
        return publishedBooks;
    }

    public void showInTables(List<LibraryBook> libraryBooks) {
        iSubscriberView.getTitleColumn().setCellValueFactory(new PropertyValueFactory<>("title"));
        iSubscriberView.getAuthorColumn().setCellValueFactory(new PropertyValueFactory<>("author"));
        iSubscriberView.getDomainColumn().setCellValueFactory(new PropertyValueFactory<>("domain"));
        iSubscriberView.getIdColumn().setCellValueFactory(new PropertyValueFactory<>("ID"));
        iSubscriberView.getIsbnColumn().setCellValueFactory(new PropertyValueFactory<>("isbn"));
        iSubscriberView.getPublisherColumn().setCellValueFactory(new PropertyValueFactory<>("publisher"));
        iSubscriberView.getPublicationYearColumn().setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.setAll(getBooks(libraryBooks));
        iSubscriberView.getBooksTable().setItems(books);
        ObservableList<PublishedBook> publishedBooks = FXCollections.observableArrayList();
        publishedBooks.setAll(getPublishedBooks(libraryBooks));
        iSubscriberView.getPublishedBooksTable().setItems(publishedBooks);
    }

    public void showAllBooks() throws JAXBException {
        showInTables(persistenceLibrary.getLibraryBooks(iSubscriberView.getLibraryID()));
    }

    public void filterInitialization() {
        EventHandler<ActionEvent> selectFilterEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iSubscriberView.setFilterType(((MenuItem) event.getSource()).getText());
                try {
                    showInTables(filterBooks());
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        };
        MenuItem item1 = new MenuItem("disponibilitate");
        MenuItem item2 = new MenuItem("domeniu");
        MenuItem item3 = new MenuItem("autor");
        MenuItem item4 = new MenuItem("editura");
        item1.setOnAction(selectFilterEvent);
        item2.setOnAction(selectFilterEvent);
        item3.setOnAction(selectFilterEvent);
        item4.setOnAction(selectFilterEvent);
        iSubscriberView.getFilterButton().getItems().addAll(item1, item2, item3, item4);
    }

    public List<LibraryBook> filterBooks()
            throws JAXBException {
        switch (iSubscriberView.getFilterType()) {
            case "disponibilitate":
                List<LibraryBook> libraryBooks = persistenceLibrary.getLibraryBooks(iSubscriberView.getLibraryID());
                for (LibraryBook libraryBook : libraryBooks) {
                    for (Loan loan : persistenceLoan.readXML()) {
                        libraryBook.getPublishedBooks().removeIf(publishedBook -> publishedBook.getID() == loan.getBookID()
                                && loan.isActive());
                    }
                }
                return libraryBooks;
            case "domeniu":
                return persistenceLibrary.filter(iSubscriberView.getLibraryID(), iSubscriberView.getFilter(), "",
                        "");
            case "autor":
                return persistenceLibrary.filter(iSubscriberView.getLibraryID(), "", iSubscriberView.getFilter(),
                        "");
            case "editura":
                return persistenceLibrary.filter(iSubscriberView.getLibraryID(), "", "",
                        iSubscriberView.getFilter());
        }
        return null;
    }

    public void searchBooksByTitle() throws JAXBException {
        List<LibraryBook> libraryBooks =
                persistenceLibrary.searchByTitle(iSubscriberView.getLibraryID(), iSubscriberView.getSearchTitle());
        showInTables(libraryBooks);
    }

    public User login() throws JAXBException {
        return persistenceUser.validateUser(iSubscriberView.getUsername(), iSubscriberView.getPassword());
    }
}

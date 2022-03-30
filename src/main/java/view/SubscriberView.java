package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import model.LibraryBook;
import model.PublishedBook;
import model.User;
import presenter.SubscriberPresenter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class SubscriberView implements ISubscriberView {
    private Stage stage;
    private long libraryID;
    private String filterType;
    @FXML
    protected TableView<Book> booksTable;
    @FXML
    protected TableView<PublishedBook> publishedBooksTable;
    @FXML
    protected TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> domainColumn;
    @FXML
    private TableColumn<PublishedBook, Long> idColumn;
    @FXML
    private TableColumn<PublishedBook, Integer> isbnColumn;
    @FXML
    private TableColumn<PublishedBook, String> publisherColumn;
    @FXML
    private TableColumn<PublishedBook, Integer> publicationYearColumn;
    @FXML
    private MenuButton filterButton;
    @FXML
    private TextField filterField;
    @FXML
    private TextField searchTitleField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;


    public SubscriberView() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public long getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(long libraryID) {
        this.libraryID = libraryID;
    }

    @Override
    public String getFilterType() {
        return filterType;
    }

    @Override
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    @Override
    public TableView<Book> getBooksTable() {
        return booksTable;
    }

    @Override
    public TableView<PublishedBook> getPublishedBooksTable() {
        return publishedBooksTable;
    }

    @Override
    public TableColumn<Book, String> getTitleColumn() {
        return titleColumn;
    }

    @Override
    public TableColumn<Book, String> getAuthorColumn() {
        return authorColumn;
    }

    @Override
    public TableColumn<Book, String> getDomainColumn() {
        return domainColumn;
    }

    @Override
    public TableColumn<PublishedBook, Long> getIdColumn() {
        return idColumn;
    }

    @Override
    public TableColumn<PublishedBook, Integer> getIsbnColumn() {
        return isbnColumn;
    }

    @Override
    public TableColumn<PublishedBook, String> getPublisherColumn() {
        return publisherColumn;
    }

    @Override
    public TableColumn<PublishedBook, Integer> getPublicationYearColumn() {
        return publicationYearColumn;
    }

    public MenuButton getFilterButton() {
        return filterButton;
    }

    @Override
    public String getFilter() {
        return filterField.getText();
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public String getSearchTitle() {
        return searchTitleField.getText();
    }

    @Override
    public List<LibraryBook> getFilteredBooks() throws JAXBException {
        SubscriberPresenter subscriberPresenter = new SubscriberPresenter(this);
        return subscriberPresenter.filterBooks();
    }

    public void filters() {
        SubscriberPresenter subscriberPresenter = new SubscriberPresenter(this);
        subscriberPresenter.filterInitialization();
    }

    public void viewBooksButton(ActionEvent actionEvent) throws JAXBException {
        SubscriberPresenter subscriberPresenter = new SubscriberPresenter(this);
        subscriberPresenter.showAllBooks();
    }

    public void searchBookButton(ActionEvent actionEvent) throws JAXBException {
        SubscriberPresenter subscriberPresenter = new SubscriberPresenter(this);
        subscriberPresenter.searchBooksByTitle();
    }

    public void openWindow(String resource, String title, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(resource));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene newScene = new Scene(anchorPane);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle(title);
        switch (user.getType()) {
            case ADMINISTRATOR:
                AdminView adminView = fxmlLoader.getController();
                adminView.setStage(newStage);
                adminView.setLibraryID(libraryID);
                adminView.filters();
                break;
            case LIBRARIAN:
                LibrarianView librarianView = fxmlLoader.getController();
                librarianView.setStage(newStage);
                librarianView.setLibraryID(libraryID);
                librarianView.filters();
                break;
        }
        newStage.show();
    }

    public void loginButton(ActionEvent actionEvent) throws JAXBException {
        SubscriberPresenter subscriberPresenter = new SubscriberPresenter(this);
        User user = subscriberPresenter.login();
        try {
            if (user != null) {
                switch (user.getType()) {
                    case ADMINISTRATOR:
                        openWindow("/adminView/admin.fxml", "Administrator", user);
                        break;
                    case LIBRARIAN:
                        openWindow("/librarianView/librarian.fxml", "Bibliotecar", user);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Date incorecte!");
        }
    }
}

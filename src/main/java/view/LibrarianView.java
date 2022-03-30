package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import model.PublishedBook;
import presenter.LibrarianPresenter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class LibrarianView extends SubscriberView implements ILibrarianView {
    public TextField domainField;
    public TextField authorField;
    public TextField yearField;
    public TextField publisherField;
    public TextField ISBNField;
    public TextField IDField;
    public TextField searchTitleField;
    public TextField libraryID;
    public TextField bookID;
    public TextField bookIdField;
    public TextField subscriberIdField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField CNPfield;
    private Stage stage;

    public LibrarianView() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public List<Book> getBooks() {
        return booksTable.getItems();
    }

    @Override
    public List<PublishedBook> getPublishedBooks() {
        return publishedBooksTable.getItems();
    }

    @Override
    public String getCNP() {
        return CNPfield.getText();
    }

    @Override
    public String getName() {
        return nameField.getText();
    }

    @Override
    public String getTitle() {
        return titleField.getText();
    }

    @Override
    public String getAuthor() {
        return authorField.getText();
    }

    @Override
    public String getDomain() {
        return domainField.getText();
    }

    @Override
    public String getYear() {
        return yearField.getText();
    }

    @Override
    public String getPublisher() {
        return publisherField.getText();
    }

    @Override
    public String getISBN() {
        return ISBNField.getText();
    }

    @Override
    public String getID() {
        return IDField.getText();
    }

    @Override
    public String getBookID() {
        return bookID.getText();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getSearchTitle() {
        return searchTitleField.getText();
    }

    @Override
    public String getSubscriberID() {
        return subscriberIdField.getText();
    }

    public void changeScene(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(resource));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        LibrarianView view = fxmlLoader.getController();
        view.setStage(stage);
        view.setLibraryID(getLibraryID());
        view.filters();
    }

    public void viewBooksButton2(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarian.fxml");
    }

    public void addBookButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianAddBook.fxml");
    }

    public void addBook(ActionEvent actionEvent) {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.addBook();
    }

    public void editBookButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianEditBook.fxml");
    }

    public void editBook(ActionEvent actionEvent) throws JAXBException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.editBook();
    }

    public void deleteBookButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianDeleteBook.fxml");
    }

    public void deleteBook(ActionEvent actionEvent) throws JAXBException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.deleteBook();
    }

    public void addSubscriberButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianAddSubscriber.fxml");
    }

    public void addSubscriber(ActionEvent actionEvent) {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.addSubscriber();
    }

    public void editSubscriberButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianEditSubscriber.fxml");
    }

    public void editSubscriber(ActionEvent actionEvent) throws JAXBException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.editSubscriber();
    }

    public void deleteSubscriberButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianDeleteSubscriber.fxml");
    }

    public void deleteSubscriber(ActionEvent actionEvent) throws JAXBException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.deleteSubscriber();
    }

    public void saveCSVButton(ActionEvent actionEvent) throws JAXBException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.generateCSV();
    }

    public void saveJSONButton(ActionEvent actionEvent) throws JAXBException, IOException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.generateJSON();
    }

    public void borrowBookButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianLoan.fxml");
    }

    public void borrowBook(ActionEvent actionEvent) throws JAXBException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.borrowBook();
    }

    public void returnBookButton(ActionEvent actionEvent) throws IOException {
        changeScene("/librarianView/librarianLoan.fxml");
    }

    public void returnBook(ActionEvent actionEvent) throws JAXBException {
        LibrarianPresenter librarianPresenter = new LibrarianPresenter(this);
        librarianPresenter.returnBook();
    }
}

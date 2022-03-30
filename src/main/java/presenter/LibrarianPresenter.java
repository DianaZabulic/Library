package presenter;

import model.*;
import view.ILibrarianView;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class LibrarianPresenter {
    ILibrarianView iLibrarianView;
    PersistenceLibrary persistenceLibrary;
    PersistenceUser persistenceUser;
    PersistenceLoan persistenceLoan;

    public LibrarianPresenter() {
    }

    public LibrarianPresenter(ILibrarianView iLibrarianView) {
        this.iLibrarianView = iLibrarianView;
        persistenceUser = new PersistenceUser();
        persistenceLibrary = new PersistenceLibrary();
        persistenceLoan = new PersistenceLoan();
    }

    public void addSubscriber() {
        persistenceUser.addUser(new User(Integer.parseInt(iLibrarianView.getCNP()), iLibrarianView.getName()));
    }

    public void editSubscriber() throws JAXBException {
        persistenceUser.updateUser(new User(Integer.parseInt(iLibrarianView.getCNP()), iLibrarianView.getName()));
    }

    public void deleteSubscriber() throws JAXBException {
        persistenceUser.deleteUser(Integer.parseInt(iLibrarianView.getCNP()));
    }

    public void addBook() {
        persistenceLibrary.addBook(iLibrarianView.getLibraryID(), iLibrarianView.getTitle(),
                iLibrarianView.getAuthor(), iLibrarianView.getDomain(), Long.parseLong(iLibrarianView.getID()),
                Integer.parseInt(iLibrarianView.getISBN()), iLibrarianView.getPublisher(),
                Integer.parseInt(iLibrarianView.getYear()));
    }

    public void editBook() throws JAXBException {
        persistenceLibrary.updateBook(iLibrarianView.getLibraryID(), Long.parseLong(iLibrarianView.getBookID()),
                Integer.parseInt(iLibrarianView.getISBN()), iLibrarianView.getPublisher(),
                Integer.parseInt(iLibrarianView.getYear()));
    }

    public void deleteBook() throws JAXBException {
        persistenceLibrary.deleteBook(iLibrarianView.getLibraryID(), Integer.parseInt(iLibrarianView.getBookID()));
    }

    public void generateCSV() {
        ReportCSV reportCSV = new ReportCSV();
        reportCSV.generateReport(iLibrarianView.getBooks(), iLibrarianView.getPublishedBooks());
    }

    public void generateJSON() throws JAXBException, IOException {
        ReportJSON reportJSON = new ReportJSON();
        reportJSON.generateReport(iLibrarianView.getFilteredBooks());
    }

    public void borrowBook() throws JAXBException {
        persistenceLoan.borrowBook(Integer.parseInt(iLibrarianView.getBookID()),
                Integer.parseInt(iLibrarianView.getSubscriberID()));

    }

    public void returnBook() throws JAXBException {
        persistenceLoan.returnBook(Integer.parseInt(iLibrarianView.getBookID()),
                Integer.parseInt(iLibrarianView.getSubscriberID()));
    }
}

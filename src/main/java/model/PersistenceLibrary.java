package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persistenceLibrary")
public class PersistenceLibrary {
    File fileName = new File("carti.xml");
    @XmlElement(name = "libraries")
    List<Library> libraries;

    public List<Library> readXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PersistenceLibrary.class);
        PersistenceLibrary persistenceLibrary = (PersistenceLibrary) context.createUnmarshaller().unmarshal(fileName);
        return persistenceLibrary.libraries;
    }

    public void writeXML(List<Library> libraries) {
        try {
            this.libraries = libraries;
            JAXBContext context = JAXBContext.newInstance(PersistenceLibrary.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, fileName);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<LibraryBook> getLibraryBooks(long libraryID) throws JAXBException {
        libraries = readXML();
        for (Library library : libraries) {
            if (library.getID() == libraryID) {
                return library.getLibraryBooks();
            }
        }
        return null;
    }

    public boolean addBook(long libraryID, String title, String author, String domain, long ID, int isbn,
                           String publisher, int publicationYear) {
        try {
            boolean added = false;
            libraries = readXML();
            for (Library library : libraries) {
                if (library.getID() == libraryID) {
                    for (LibraryBook libraryBook : library.getLibraryBooks()) {
                        Book book = libraryBook.getBook();
                        if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                            libraryBook.addToList(new PublishedBook(ID, isbn, publisher, publicationYear));
                            added = true;
                        }
                    }
                }
            }
            if (!added) {
                libraries = readXML();
                for (Library library : libraries) {
                    if (library.getID() == libraryID) {
                        List<PublishedBook> publishedBooks = new ArrayList<>();
                        publishedBooks.add(new PublishedBook(ID, isbn, publisher, publicationYear));
                        library.addToList(new LibraryBook(new Book(title, author, domain), publishedBooks));
                    }
                }
            }
            writeXML(libraries);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBook(long libraryID, long ID, int isbn, String publisher, int publicationYear)
            throws JAXBException {
        libraries = readXML();
        for (Library library : libraries) {
            if (library.getID() == libraryID) {
                for (LibraryBook libraryBook : library.getLibraryBooks()) {
                    for (PublishedBook publishedBook : libraryBook.getPublishedBooks()) {
                        if (publishedBook.getID() == ID) {
                            if (isbn != -1) {
                                publishedBook.setIsbn(isbn);
                            }
                            if (!publisher.equals("")) {
                                publishedBook.setPublisher(publisher);
                            }
                            if (publicationYear != -1) {
                                publishedBook.setPublicationYear(publicationYear);
                            }
                            writeXML(libraries);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteBook(long libraryID, int publishedBookID) throws JAXBException {
        libraries = readXML();
        for (Library library : libraries) {
            if (library.getID() == libraryID) {
                List<LibraryBook> libraryBooks = library.getLibraryBooks();
                for (LibraryBook libraryBook : libraryBooks) {
                    List<PublishedBook> publishedBooks = libraryBook.getPublishedBooks();
                    for (PublishedBook publishedBook : publishedBooks) {
                        if (publishedBook.getID() == publishedBookID) {
                            publishedBooks.remove(publishedBooks.indexOf(publishedBook));
                            libraryBook.setPublishedBooks(publishedBooks);
                            if (publishedBooks.isEmpty()) {
                                libraryBooks.remove(libraryBooks.indexOf(libraryBook));
                                library.setLibraryBooks(libraryBooks);
                            }
                            writeXML(libraries);
                            return true;
                        }
//                    List<PublishedBook> newPublishedBooks = libraryBook.getPublishedBooks();
//                    newPublishedBooks.removeIf(publishedBook -> publishedBook.getID() == publishedBookID);
//                    if (newPublishedBooks.isEmpty()) {
//                        List<LibraryBook> libraryBooks = library.getLibraryBooks();
////                        libraryBooks.remove(libraryBook);
//                        libraryBooks.remove(libraryBooks.indexOf(libraryBook));
//                        library.setLibraryBooks(libraryBooks);
//                    } else {
//                        libraryBook.setPublishedBooks(newPublishedBooks);
//                    }
//                    writeXML(libraries);
//                    return true;
                    }
                }
            }
        }
        return false;
    }

    public List<LibraryBook> searchByTitle(long libraryID, String title) throws JAXBException {
        libraries = readXML();
        List<LibraryBook> newBooks = new ArrayList<>();
        for (Library library : libraries) {
            if (library.getID() == libraryID) {
                for (LibraryBook libraryBook : library.getLibraryBooks()) {
                    if (libraryBook.getBook().getTitle().contains(title)) {
                        newBooks.add(libraryBook);
                    }
                }
            }
        }
        return newBooks;
    }

    public List<LibraryBook> filter(long libraryID, String domain, String author, String publisher)
            throws JAXBException {
        libraries = readXML();
        for (Library library : libraries) {
            if (library.getID() == libraryID) {
                List<LibraryBook> books = library.getLibraryBooks();
                if (!domain.equals("")) {
                    books = books.stream().filter(libraryBook ->
                            libraryBook.getBook().getDomain().equals(domain)).collect(Collectors.toList());
                }
                if (!author.equals("")) {
                    books = books.stream().filter(libraryBook ->
                            libraryBook.getBook().getAuthor().equals(author)).collect(Collectors.toList());
                }
                if (!publisher.equals("")) {
                    for (LibraryBook libraryBook : books) {
                        List<PublishedBook> publishedBooks = libraryBook.getPublishedBooks().stream().filter(publishedBook ->
                                publishedBook.getPublisher().equals(publisher)).collect(Collectors.toList());
                        libraryBook.setPublishedBooks(publishedBooks);
                    }
                }
                return books;
            }
        }
        return null;
    }
}

package presenter;

import model.Library;
import model.PersistenceLibrary;
import view.ILibraryView;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class LibraryPresenter {
    ILibraryView iLibraryView;
    PersistenceLibrary persistenceLibrary;

    public LibraryPresenter(ILibraryView iLibraryView) {
        this.iLibraryView = iLibraryView;
        persistenceLibrary = new PersistenceLibrary();
    }

    public List<String> getLibrariesNames() throws JAXBException {
        List<String> names = new ArrayList<>();
        for (Library library: persistenceLibrary.readXML()) {
            names.add(library.getName());
        }
        return names;
    }

    public long getLibraryIDByName(String name) throws JAXBException {
        for(Library library: persistenceLibrary.readXML()){
            if(library.getName().equals(name)){
                return library.getID();
            }
        }
        return -1;
    }
}

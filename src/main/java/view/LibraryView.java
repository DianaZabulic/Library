package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presenter.LibraryPresenter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryView implements ILibraryView {
    @FXML
    private MenuButton selectLibraryButton = new MenuButton();
    private Stage stage;

    public LibraryView() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void enterLibrary(long libraryID) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/subscriberView/subscriber.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        SubscriberView view = fxmlLoader.getController();
        view.setStage(newStage);
        view.setLibraryID(libraryID);
        view.filters();
        newStage.setTitle("Abonat");
        newStage.show();
    }

    public void selectLibraries() throws JAXBException {
        LibraryPresenter libraryPresenter = new LibraryPresenter(this);
        EventHandler<ActionEvent> selectLibraryEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String libraryName = ((MenuItem) event.getSource()).getText();
                try {
                    enterLibrary(libraryPresenter.getLibraryIDByName(libraryName));
                } catch (IOException | JAXBException e) {
                    e.printStackTrace();
                }
            }
        };
        List<String> names = libraryPresenter.getLibrariesNames();
        List<MenuItem> menuItems = new ArrayList<>();
        for (String name : names) {
            MenuItem item = new MenuItem(name);
            item.setOnAction(selectLibraryEvent);
            menuItems.add(item);
        }
        selectLibraryButton.setText("Biblioteca:");
        selectLibraryButton.getItems().addAll(menuItems);
    }
}

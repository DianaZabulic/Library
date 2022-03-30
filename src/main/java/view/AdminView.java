package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presenter.AdminPresenter;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class AdminView extends SubscriberView implements IAdminView{
    public TextField searchTitleField;
    public TextField nameField;
    public TextField CNPField;
    public TextField usernameField;
    public TextField passwordField;
    public TextField typeField;
    private Stage stage;

    public AdminView() {
        super();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
    public String getNameField() {
        return nameField.getText();
    }

    @Override
    public String getCNPField() {
        return CNPField.getText();
    }

    @Override
    public String getUsernameField() {
        return usernameField.getText();
    }

    @Override
    public String getPasswordField() {
        return passwordField.getText();
    }

    @Override
    public String getTypeField() {
        return typeField.getText();
    }

    public void changeScene(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(resource));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        AdminView view = fxmlLoader.getController();
        view.setStage(stage);
    }

    public void addUserButton(ActionEvent actionEvent) throws IOException {
        changeScene("/adminView/adminAdd.fxml");
    }

    public void addUser(ActionEvent actionEvent) {
        AdminPresenter adminPresenter = new AdminPresenter(this);
        adminPresenter.addUser();
    }

    public void editUserButton(ActionEvent actionEvent) throws IOException {
        changeScene("/adminView/adminUpdate.fxml");
    }

    public void editUser(ActionEvent actionEvent) throws JAXBException {
        AdminPresenter adminPresenter = new AdminPresenter(this);
        adminPresenter.editUser();
    }

    public void deleteUserButton(ActionEvent actionEvent) throws IOException {
        changeScene("/adminView/adminDelete.fxml");
    }

    public void deleteUser(ActionEvent actionEvent) throws JAXBException {
        AdminPresenter adminPresenter = new AdminPresenter(this);
        adminPresenter.deleteUser();
    }
}

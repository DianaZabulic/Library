import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.LibraryView;

public class FxMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/primaryView.fxml"));
        AnchorPane anchorPane= fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        LibraryView libraryView = fxmlLoader.getController();
        libraryView.setStage(primaryStage);
        libraryView.selectLibraries();
        primaryStage.setTitle("Biblioteca");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

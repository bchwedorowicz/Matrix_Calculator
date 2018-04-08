package pl.beata.matrix.main;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	final String appName = "Matrix Calculator v0.1";
	try {
	    URL source = getClass().getResource("/pl/beata/matrix/view/MatrixPane.fxml");
	    Parent parent = (Parent) FXMLLoader.load(source);
	    Scene scene = new Scene(parent);
	    primaryStage.setTitle(appName);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void main(String[] args) {
	launch(args);

    }

}

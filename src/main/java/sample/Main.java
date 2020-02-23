package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Reader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Person Registry");
        primaryStage.setScene(new Scene(root, 750, 650));
        primaryStage.show();
    }



    public static void main(String[] args){
        launch(args);

        Reader reader = new Reader() {
            @Override
            public int read(char[] chars, int i, int i1) throws IOException {
                return 0;
            }

            @Override
            public void close() throws IOException {

            }
        };

        try {

        } catch (InvalidPersonFormat e){

        }

    }
}

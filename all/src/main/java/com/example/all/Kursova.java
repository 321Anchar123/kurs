package com.example.all;

import com.example.all.Files.Filetxt;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Kursova extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Kursova.class.getResource("Kursova.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("");//Название
        stage.setScene(scene);
        stage.show();
        Filetxt filetxt = new Filetxt();
        stage.getIcons().add(new Image(filetxt.getIcon()));
    }
    public static void main(String[] args) {launch();}
}

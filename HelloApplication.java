package com.example.demo;

import com.example.demo.DataModel.BossData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Welcome to the Grind Machine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
       try {
           BossData.getInstance().loadList();
       } catch (IOException e) {
           System.out.println(e.getMessage());;
       }
    }

    @Override
    public void stop() throws Exception {
        try {
            BossData.getInstance().saveList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
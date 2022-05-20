package com.example.supervisionnetworkInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    public Button connexion;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void connection(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Gestionaire.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 774, 529);
            Stage stage = new Stage();
            stage.setTitle("Gestionaire");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Cant load window");
        }
    }
}
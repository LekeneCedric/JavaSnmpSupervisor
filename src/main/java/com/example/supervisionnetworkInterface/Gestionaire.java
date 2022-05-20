package com.example.supervisionnetworkInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gestionaire {

    public Pane ginterfaceGest;



    public void rparadd(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("R_par_Add.fxml"));
        ginterfaceGest.getChildren().removeAll();
        ginterfaceGest.getChildren().setAll(fxmlLoader);
    }

    public void rparplage(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("RparPlge.fxml"));
        ginterfaceGest.getChildren().removeAll();
        ginterfaceGest.getChildren().setAll(fxmlLoader);
    }

    public void rcmplte(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("RCmplte.fxml"));
        ginterfaceGest.getChildren().removeAll();
        ginterfaceGest.getChildren().setAll(fxmlLoader);
    }

    public void ajoutmachine(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AjoutMachine.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 150);
            Stage stage = new Stage();
            stage.setTitle("Ajout Machine");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Cant load window");
        }
    }

    public void supmachine(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SupprimerMachine.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 150);
            Stage stage = new Stage();
            stage.setTitle("Supprimer Machine");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Cant load window");
        }
    }
}

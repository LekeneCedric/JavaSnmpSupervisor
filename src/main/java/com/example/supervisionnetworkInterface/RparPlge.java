package com.example.supervisionnetworkInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RparPlge {
    public void getIpInfo(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("getIpinfo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 589, 373);
            Stage stage = new Stage();
            stage.setTitle("Info");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Cant load window");
        }
    }

    public void scanparplage(ActionEvent actionEvent) {
    }
}

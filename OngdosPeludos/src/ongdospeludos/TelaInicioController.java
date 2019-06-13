/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cris
 */
public class TelaInicioController implements Initializable {
    
    @FXML
    private Button entrarButton, registrarButton;
    @FXML
    private Label titulo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titulo.setFont(Font.font("Staatliches", 35));
    }    
    
    @FXML
    public void entrarButton(ActionEvent event){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("TodosAnimais.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) entrarButton.getScene().getWindow();
        stage.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cris
 */
public class TodosAnimaisController implements Initializable {

    @FXML
    private Button voltarButton, addAnimalButton;
    @FXML
    private ComboBox<Animal> tipoCBox, porteCBox, sexoCBox, racaCBox, idadeCBox;
    private ObservableList<Animal> tipoLista = FXCollections.observableArrayList(), 
            porteLista = FXCollections.observableArrayList(), 
            sexoLista = FXCollections.observableArrayList(), 
            racaLista = FXCollections.observableArrayList(), 
            idadeLista = FXCollections.observableArrayList();
    @FXML
    private RadioButton castradoRadioButton;
    @FXML
    private TextField nomePesquisa;
    @FXML
    private ListView<Animal> animaisListView;
    private ObservableList<Animal> animaisList = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoCBox.setItems(tipoLista);
        porteCBox.setItems(porteLista);
        sexoCBox.setItems(sexoLista);
        racaCBox.setItems(racaLista);
        idadeCBox.setItems(idadeLista);
        animaisListView.setCellFactory((list) -> {
            return new ListCell<Animal>() {
                @Override
                protected void updateItem(Animal item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getNome());
                    }
                }
            };
        });
    }    
    
    public void voltarButton(ActionEvent event){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("TelaInicio.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }
    
    public void addAnimalButton(ActionEvent event){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("AdicionarAnimal.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) addAnimalButton.getScene().getWindow();
        stage.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static ongdospeludos.ManipularArquivo.leArq;

/**
 * FXML Controller class
 *
 * @author WINDOWS10
 */
public class EditarAnimalController implements Initializable {

    @FXML
    private Button concluirButton, cancelarButton, addFotoButton;
    @FXML
    private ComboBox<String> tipoCBox, racaCBox, porteCBox, sexoCBox;
    private ObservableList<String> tipoLista = FXCollections.observableArrayList(), 
            racaLista = FXCollections.observableArrayList(), 
            porteLista = FXCollections.observableArrayList(),
            sexoLista = FXCollections.observableArrayList();
    @FXML
    private TextField nomeTextField, idadeTextField;
    @FXML
    private ImageView fotoImageView;
    private Image foto;
    private FileChooser fileChooser = new FileChooser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idadeTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                idadeTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        tipoLista.addAll("Gato", "Cachorro");
        tipoCBox.setItems(tipoLista);
        porteLista.addAll("Pequeno", "Médio", "Grande");
        porteCBox.setItems(porteLista);
        sexoLista.addAll("Macho", "Fêmea");
        sexoCBox.setItems(sexoLista);
    }    
    
    public void cancelarButton(ActionEvent event){
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
        stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
    
    public void tipoCBox (ActionEvent event){
        try {
            racaLista = leArq("racasDe" + tipoCBox.getSelectionModel().getSelectedItem() + ".txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        racaCBox.setItems(racaLista);
    }
    
    @FXML
    private void addFotoButton(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("File Chooser Sample");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            foto = new Image(file.toURI().toString());
            fotoImageView.setImage(foto);
        }
    }
    
}

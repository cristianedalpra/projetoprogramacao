/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import ongdospeludos.ongdb.Animal;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static ongdospeludos.Funçoes.mostraAlerta;
import ongdospeludos.ongdb.DaoAnimal;
import ongdospeludos.ongdb.DaoRaça;
import ongdospeludos.ongdb.Raça;

/**
 * FXML Controller class
 *
 * @author Cris
 */
public class TodosAnimaisController implements Initializable {

    private DaoAnimal daoAnimal = new DaoAnimal();
    private DaoRaça daoRaça = new DaoRaça();

    @FXML
    private Button voltarButton, addAnimalButton;
    @FXML
    private ComboBox<String> porteCBox, sexoCBox, especieCBox;
    private ObservableList<String> porteLista = FXCollections.observableArrayList(),
            sexoLista = FXCollections.observableArrayList(),
            especieLista = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> idadeCBox;
    private ObservableList<Integer> idadeLista = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Raça> raçaCBox;
    private ObservableList<Raça> raçaLista = FXCollections.observableArrayList();
    @FXML
    private CheckBox castradoCheckBox;
    @FXML
    private TextField nomePesquisa;
    @FXML
    private ListView<Animal> animaisListView;
    private ObservableList<Animal> animaisLista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        especieLista = daoRaça.pesquisaEspecies();
        especieCBox.setItems(especieLista);
        porteCBox.setItems(porteLista);
        sexoCBox.setItems(sexoLista);
        idadeCBox.setItems(idadeLista);
        animaisLista = daoAnimal.pesquisaTodos();
        animaisListView.setItems(animaisLista);
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
        animaisListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Animal> observable, Animal oldValue, Animal newValue) -> {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("VisualizarAnimal.fxml"));
            try {
                Loader.load();
            } catch (IOException ex) {
                mostraAlerta(Alert.AlertType.ERROR,
                        "Erro ao trocar de janela.",
                        "ERRO");
            }
            VisualizarAnimalController display = Loader.getController();
            display.passaAnimalId(newValue.getId());
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
            stage = (Stage) animaisListView.getScene().getWindow();
            stage.close();
        });
    }

    @FXML
    public void voltarButton(ActionEvent event) {
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

    @FXML
    public void addAnimalButton(ActionEvent event) {
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

    public void especieCBox(ActionEvent event) {
        raçaLista = daoRaça.pesquisaRaçasExistentes(especieCBox.getSelectionModel().getSelectedItem());
        raçaCBox.setItems(raçaLista);
        raçaCBox.setCellFactory((list) -> {
            return new ListCell<Raça>() {
                @Override
                protected void updateItem(Raça item, boolean empty) {
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
}

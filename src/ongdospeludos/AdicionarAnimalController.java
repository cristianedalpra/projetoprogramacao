/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static ongdospeludos.Funçoes.mostraAlerta;
import ongdospeludos.ongdb.Animal;
import ongdospeludos.ongdb.DaoAnimal;
import ongdospeludos.ongdb.DaoRaça;
import ongdospeludos.ongdb.Raça;
import org.controlsfx.control.ToggleSwitch;

/**
 * FXML Controller class
 *
 * @author WINDOWS10
 */
public class AdicionarAnimalController implements Initializable {

    DaoRaça daoRaça = new DaoRaça();
    DaoAnimal daoAnimal = new DaoAnimal();
    private int animalId;

    @FXML
    private Button concluirButton, cancelarButton, addFotoButton;
    @FXML
    private Label tituloLabel;
    @FXML
    private ComboBox<String> especieCBox, porteCBox, sexoCBox;
    private ObservableList<String> especieLista = FXCollections.observableArrayList(),
            porteLista = FXCollections.observableArrayList(),
            sexoLista = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Raça> raçaCBox;
    private ObservableList<Raça> raçaLista;
    @FXML
    private TextField nomeTextField, idadeTextField;
    @FXML
    private ToggleSwitch castradoToggleSwitch;
    @FXML
    private ImageView fotoImageView;
    private Image foto;
    private String caminhoFoto;
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idadeTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                idadeTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        especieLista = daoRaça.pesquisaEspecies();
        especieCBox.setItems(especieLista);
        porteLista.addAll("Pequeno", "Médio", "Grande");
        porteCBox.setItems(porteLista);
        sexoLista.addAll("Macho", "Fêmea");
        sexoCBox.setItems(sexoLista);
    }

    public void cancelarButton(ActionEvent event) {
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

    public void especieCBox(ActionEvent event) {
        raçaCBox.valueProperty().set(null);
        setRaçaCBox();
    }

    @FXML
    private void addFotoButton(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("File Chooser Sample");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            caminhoFoto = file.toURI().toString();
            foto = new Image(file.toURI().toString());
            fotoImageView.setImage(foto);
        }
    }

    @FXML
    private void concluirButton(ActionEvent event) {
        Animal animal = new Animal();
        if (verificador()) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Está tudo correto?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> bt = alerta.showAndWait();
            if (bt.get() == ButtonType.YES) {
                animal.setNome(nomeTextField.getText());
                animal.setRaçaId(raçaCBox.getSelectionModel().getSelectedItem().getId());
                animal.setPorte(porteCBox.getSelectionModel().getSelectedItem());
                animal.setSexo(sexoCBox.getSelectionModel().getSelectedItem());
                animal.setIdade(Integer.parseInt(idadeTextField.getText()));
                copiaFoto(caminhoFoto);
                animal.setCaminhoFoto(caminhoFoto);
                if (castradoToggleSwitch.isSelected()) {
                    animal.setCastrado(true);
                } else {
                    animal.setCastrado(false);
                }
                if (tituloLabel.getText().equals("Adicionar animal")) {
                    if (daoAnimal.adiciona(animal)) {
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
                        stage = (Stage) concluirButton.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    animal.setId(animalId);
                    if (daoAnimal.altera(animal)) {
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
                        display.passaAnimalId(animalId);
                        Parent p = Loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(p));
                        stage.show();
                        stage = (Stage) concluirButton.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        } else {
            mostraAlerta(AlertType.ERROR, "Você deve preencher todos os campos e selecionar uma imagem!", "ERRO");
        }
    }

    private boolean verificador() {
        if (especieCBox.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        if (porteCBox.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        if (sexoCBox.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        if (raçaCBox.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        if (caminhoFoto.isEmpty()) {
            return false;
        }
        if (nomeTextField.getText().isEmpty()) {
            return false;
        }
        return !idadeTextField.getText().isEmpty();
    }

    public void passaAnimalId(int animalId) {
        this.animalId = animalId;
        tituloLabel.setText("Editar Animal");
        Animal animal = daoAnimal.buscaAnimalPorId(animalId);
        especieCBox.getSelectionModel().select(daoRaça.pesquisaRaçaPorId(animal.getRaçaId()).getEspecie());
        setRaçaCBox();
        raçaCBox.getSelectionModel().select(daoRaça.pesquisaRaçaPorId(animal.getRaçaId()));
        porteCBox.getSelectionModel().select(animal.getPorte());
        sexoCBox.getSelectionModel().select(animal.getSexo());
        idadeTextField.setText(Integer.toString(animal.getIdade()));
        nomeTextField.setText(animal.getNome());
        if (animal.isCastrado()) {
            castradoToggleSwitch.setSelected(true);
        }
        caminhoFoto = animal.getCaminhoFoto();
        fotoImageView.setImage(new Image(animal.getCaminhoFoto()));
    }

    private void setRaçaCBox() {
        raçaLista = daoRaça.pesquisaCBox(especieCBox.getSelectionModel().getSelectedItem());
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

    public void copiaFoto(String caminhoFoto) {
        try {
            if (tituloLabel.getText().equals("Adicionar animal")) {
                Files.copy(Paths.get(caminhoFoto.substring(6)), Paths.get("src\\imagens\\animal" + Integer.toString(daoAnimal.getHighestId() + 1) + caminhoFoto.substring(caminhoFoto.lastIndexOf("."))), REPLACE_EXISTING);
                this.caminhoFoto = "src\\imagens\\animal" + Integer.toString(daoAnimal.getHighestId() + 1) + caminhoFoto.substring(caminhoFoto.lastIndexOf("."));
            } else {
                Files.copy(Paths.get(caminhoFoto), Paths.get(daoAnimal.buscaAnimalPorId(animalId).getCaminhoFoto()), REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            System.out.println("nao deu pra copia");
        }
    }
}

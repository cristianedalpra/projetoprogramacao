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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static ongdospeludos.Funçoes.mostraAlerta;
import ongdospeludos.ongdb.Animal;
import ongdospeludos.ongdb.DaoAnimal;
import ongdospeludos.ongdb.DaoRaça;

/**
 * FXML Controller class
 *
 * @author WINDOWS10
 */
public class VisualizarAnimalController implements Initializable {

    private DaoAnimal daoAnimal = new DaoAnimal();
    private DaoRaça daoRaça = new DaoRaça();

    @FXML
    private Label nomeLabel, especieLabel, raçaLabel, porteLabel, idadeLabel, sexoLabel, castradoLabel;
    @FXML
    private Button voltarButton, editarButton;
    @FXML
    private ImageView fotoImageView;
    private Animal animal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void passaAnimalId(int animalId) {
        animal = daoAnimal.buscaAnimalPorId(animalId);
        nomeLabel.setText(animal.getNome());
        especieLabel.setText(daoRaça.pesquisaRaçaPorId(animal.getRaçaId()).getEspecie());
        raçaLabel.setText(daoRaça.pesquisaRaçaPorId(animal.getRaçaId()).getNome());
        porteLabel.setText(animal.getPorte());
        idadeLabel.setText(Integer.toString(animal.getIdade()) + anoOuAnos(animal.getIdade()));
        sexoLabel.setText(animal.getSexo());
        if (animal.isCastrado()) {
            castradoLabel.setText("Castrado");
        } else {
            castradoLabel.setText("Não castrado");
        }
        fotoImageView.setImage(new Image(animal.getCaminhoFoto()));
    }

    public String anoOuAnos(int idade) {
        if (idade > 1) {
            return " anos";
        }
        return " ano";
    }

    @FXML
    public void voltarButton(ActionEvent event) {
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
        stage = (Stage) voltarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void editarButton(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("AdicionarAnimal.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            mostraAlerta(Alert.AlertType.ERROR,
                    "Erro ao trocar de janela.",
                    "ERRO");
        }
        AdicionarAnimalController display = Loader.getController();
        display.passaAnimalId(animal.getId());
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) editarButton.getScene().getWindow();
        stage.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static ongdospeludos.Funçoes.mostraAlerta;
import ongdospeludos.ongdb.DaoUsuario;
import ongdospeludos.ongdb.Usuario;

/**
 * FXML Controller class
 *
 * @author Cris
 */
public class TelaInicioController implements Initializable {

    private DaoUsuario daoUsuario = new DaoUsuario();

    @FXML
    private Button entrarButton, registrarButton;
    @FXML
    private TextField usuarioTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label titulo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titulo.setFont(Font.font("Staatliches", 44));
        entrarButton.setDefaultButton(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usuarioTextField.requestFocus();
            }
        });
    }

    @FXML
    public void entrarButton(ActionEvent event) {
        Usuario usuario = new Usuario(usuarioTextField.getText(), passwordField.getText());
        if (!usuarioTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            if (daoUsuario.entrar(usuario)) {
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
            } else {
                mostraAlerta(Alert.AlertType.ERROR,
                        "Nome de usuário ou senha incorreto.",
                        "ERRO");
            }
        } else {
            mostraAlerta(Alert.AlertType.ERROR,
                    "Preencha todos os campos e tente novamente.",
                    "ERRO");
        }
    }

    @FXML
    public void registrarButton(ActionEvent event) {
        Usuario usuario = new Usuario(usuarioTextField.getText(), passwordField.getText(), "Membro");
        if (!usuarioTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            if (!daoUsuario.nomeEmUso(usuario)) {
                daoUsuario.adiciona(usuario);
            } else {
                mostraAlerta(Alert.AlertType.ERROR,
                        "O nome de usuário já foi utilizado.",
                        "ERRO");
            }
        } else {
            mostraAlerta(Alert.AlertType.ERROR,
                    "Preencha todos os campos e tente novamente.",
                    "ERRO");
        }
    }
}

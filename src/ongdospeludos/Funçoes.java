/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import javafx.scene.control.Alert;

/**
 *
 * @author WINDOWS10
 */
public class Funçoes {

    public static void mostraAlerta(Alert.AlertType tipo, String texto, String titulo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(texto);
        alerta.setTitle(titulo);
        alerta.showAndWait();
    }
}

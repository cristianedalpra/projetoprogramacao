/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WINDOWS10
 */
public class ManipularArquivo {
    
    public static ObservableList<String> leArq(String arquivo) throws IOException {
        ObservableList<String> lines = FXCollections.observableArrayList();
        //BufferedReader br = new BufferedReader(new FileReader(arquivo));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8));
        String line = null;
        do {
            line = br.readLine();
            if (line != null) {
                lines.add(line);
            }
        } while (line != null);
        br.close();
        return lines;
    }
}

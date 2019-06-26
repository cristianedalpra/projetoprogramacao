/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos.ongdb;

import javafx.scene.image.Image;

/**
 *
 * @author WINDOWS10
 */
public class Animal {
    
    private int id ,idade, raçaId;
    private String nome, porte, sexo, caminhoFoto;
    private boolean castrado;

    public Animal() {
    }

    public Animal(int idade, int raçaId, String nome, String porte, String sexo, String caminhoFoto, boolean castrado) {
        this.idade = idade;
        this.raçaId = raçaId;
        this.nome = nome;
        this.porte = porte;
        this.sexo = sexo;
        this.caminhoFoto = caminhoFoto;
        this.castrado = castrado;
    }

    public Animal(int id, int idade, int raçaId, String nome, String porte, String sexo, String caminhoFoto, boolean castrado) {
        this.id = id;
        this.idade = idade;
        this.raçaId = raçaId;
        this.nome = nome;
        this.porte = porte;
        this.sexo = sexo;
        this.caminhoFoto = caminhoFoto;
        this.castrado = castrado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getRaçaId() {
        return raçaId;
    }

    public void setRaçaId(int raçaId) {
        this.raçaId = raçaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", idade=" + idade + ", ra\u00e7aId=" + raçaId + ", nome=" + nome + ", porte=" + porte + ", sexo=" + sexo + ", caminhoFoto=" + caminhoFoto + ", castrado=" + castrado + '}';
    }
    
    
}

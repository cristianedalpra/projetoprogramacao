/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

import javafx.scene.image.Image;

/**
 *
 * @author WINDOWS10
 */
public class Animal {
    
    private int id ,idade;
    private String nome, porte, raça, sexo, tipo;
    private boolean castrado;
    private Image foto;

    public Animal() {
    }

    public Animal(String nome, String porte, String raça, String sexo, String tipo, int idade, boolean castrado) {
        this.nome = nome;
        this.porte = porte;
        this.raça = raça;
        this.sexo = sexo;
        this.tipo = tipo;
        this.idade = idade;
        this.castrado = castrado;
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

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    @Override
    public String toString() {
        return "Animal{" + "nome=" + nome + ", porte=" + porte + ", ra\u00e7a=" + raça + ", sexo=" + sexo + ", tipo=" + tipo + ", idade=" + idade + ", castrado=" + castrado + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos.ongdb;

/**
 *
 * @author WINDOWS10
 */
public class Raça {
    private int id;
    private String nome, especie;

    public Raça() {
    }

    public Raça(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
    }

    public Raça(int id, String nome, String especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return nome;
    }

    
    
    
    
}

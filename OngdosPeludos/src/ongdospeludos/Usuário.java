/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongdospeludos;

/**
 *
 * @author WINDOWS10
 */
public class Usuário {
    int id;
    String nome, senha;

    public Usuário() {
    }

    public Usuário(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuário(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuário{" + "id=" + id + ", nome=" + nome + ", senha=" + senha + '}';
    }
    
    
    
}

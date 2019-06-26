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
public class SQLConstantesUsuario {

    public static final String INSERT = "insert into "
            + "usuario (id, nome, senha, cargo) "
            + "values (?,?,?,?)";

    public static final String UPDATE = "update usuario set "
            + "nome=?, senha=?, cargo=? where id=?";

    public static final String REMOVE = "delete from usuario where id=?";

    public static final String SEARCH = "select * from usuario";
}

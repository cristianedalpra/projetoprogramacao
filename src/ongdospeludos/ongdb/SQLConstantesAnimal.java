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
public class SQLConstantesAnimal {

    public static final String INSERT = "insert into "
            + "animal (id, nome, porte, idade, sexo, castrado, caminhoFoto, raçaId) "
            + "values (?,?,?,?,?,?,?,?)";

    public static final String UPDATE = "update animal set "
            + "nome=?, porte=?, idade=?, sexo=?, castrado=?, caminhoFoto=?, raçaId=? where id=?";

    public static final String REMOVE = "delete from animal where id=?";

    public static final String SEARCH = "select * from animal";

    public static final String SEARCHWITHID = "select * from animal where id =?";

    public static final String SEARCHHIGHESTID = "select id from animal order by id desc limit 0, 1";

}

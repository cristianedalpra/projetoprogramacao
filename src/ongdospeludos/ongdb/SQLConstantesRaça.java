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
public class SQLConstantesRaça {
    public static final String INSERT = "insert into "
            + "raça (id, nome, especie) "
            + "values (?,?,?)";

    public static final String UPDATE = "update raça set "
            + "nome=?, especie=? where id=?";

    public static final String REMOVE = "delete from raça where id=?";

    public static final String SEARCH = "select * from raça";
    
    public static final String SEARCHCBOX = "select * from raça where especie=?";
    
    public static final String SEARCHESPECIESEXISTENTES = "select distinct especie from raça";
    
    public static final String SEARCHWITHID = "select * from raça where id =?";
    
    public static final String SEARCHRAÇASEXISTENTES = "select distinct r.id, r.nome, r.especie "
            + "from raça r, animal a "
            + "where r.id = a.raçaId and r.especie=?";
}

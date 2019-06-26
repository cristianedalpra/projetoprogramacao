package ongdospeludos.ongdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/ongdospeludos", "root", "");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        Connection con = null;
        con = getConnection();
        if (con != null) {
            System.out.println("Conectado!");

            con.close();
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author soranso
 */
public class DbConf {

    private static Connection con = null;

    public static Connection getConnection() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/invio?zeroDateTimeBehavior=convertToNull";
                String usuario = "inviousr";
                String senha = "inviousr";
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, usuario, senha);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

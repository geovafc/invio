/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author bpmlab
 */
public class ConexaoDB {
    public static Connection conectar(){
        try {
            String URL= "jdbc:mysql://localhost:3306/invio?zeroDateTimeBehavior=convertToNull";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, "root", "root");
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}

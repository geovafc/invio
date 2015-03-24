/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bpmlab.invioscript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author bpmlab
 */
public class Persistencia {
    
    private static final String URL = "jdbc:derby://localhost:1527/invio";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private final Connection connection;
    private PreparedStatement prepareStatement;

    public Persistencia() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public void salvar(Qualis qualis) throws SQLException {
        String sql = "insert into qualis values(?, ?, ?, ?, ?)";
        if (prepareStatement == null) {
            prepareStatement = connection.prepareStatement(sql);
        }
        prepareStatement.setString(1, qualis.getAreaDeAvaliacao());
        prepareStatement.setString(2, qualis.getIssn());
        prepareStatement.setString(3, qualis.getTitulo());
        prepareStatement.setString(4, qualis.getEstrato());
        prepareStatement.setString(5, qualis.getStatus());
        prepareStatement.execute();
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void close() throws SQLException {
        prepareStatement.close();
        connection.close();
    }
    
}

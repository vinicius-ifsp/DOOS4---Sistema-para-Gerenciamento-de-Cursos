package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    public PreparedStatement createStatement(String sql) throws SQLException {
        return createConnection().prepareStatement(sql);
    }
}


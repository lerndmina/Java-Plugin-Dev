package dev.lerndmina.testplugin.Utils;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private Connection  connection;
    
    public void connect(String host, int port, String database, String username, String password) throws SQLException {
        String dbString = ("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false");
        Bukkit.getLogger().info(dbString);
        connection = DriverManager.getConnection(
                dbString,
                username,
                password);
    }

    public Connection getConnection() { return  connection; }
    public void disconnect(){
        if (isConnected()){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean isConnected(){
        return connection != null;
    }
}

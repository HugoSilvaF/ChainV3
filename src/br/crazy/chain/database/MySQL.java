/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Hugo
 */
public class MySQL extends Database {

    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;
    private final boolean enable;

    /**
     * Creates a new MySQL instance
     *
     * @param plugin Plugin instance
     * @param hostname Name of the host
     * @param port Port number
     * @param database Database name
     * @param username Username
     * @param password Password
     */
    public MySQL(Plugin plugin, String hostname, String port, String database,
            String username, String password, boolean enable) {
        super(plugin);
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        this.enable = enable;
    }

    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
        return connection;
    }
    
    public boolean enable(){
        return this.enable;
    }

}

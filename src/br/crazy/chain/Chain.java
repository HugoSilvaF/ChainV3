/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain;

import br.crazy.chain.api.API;
import br.crazy.chain.arena.Arena;
import br.crazy.chain.database.MySQL;
import br.crazy.chain.util.Util;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Hugo
 */
public class Chain extends JavaPlugin {

    private static API api;
    private static MySQL mysql;
    
    private Arena arena;

    public static Chain main;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public void onEnable() {
        startMySQL();
        init();
        loadArena();
    }

    @Override
    public void onDisable() {
    }

    public API getAPI() {
        return this.api;
    }

    public MySQL getMySQL() {
        return this.mysql;
    }
    
    public void loadArena(){
        this.arena = new Arena(getConfig().getString("name"), this, getConfig().getInt("options.points_per_kill"), getConfig().getInt("options.points_per_death"), getConfig().getInt("options.health"), getConfig().getInt("limit.limit"), getConfig().getBoolean("limit.enable"));
        List<String> ses = getConfig().getStringList("spawns");
        for(String s : ses){
            this.arena.addLocation(Util.toLocation(s));
        }
    }
    
    public void init(){
        main = this;
        api = new API(this, arena);
    }
    
    private void startMySQL(){
        mysql = new MySQL(this, getConfig().getString("MySQL.host"), getConfig().getString("MySQL.port"), getConfig().getString("MySQL.database"), getConfig().getString("MySQL.username"), getConfig().getString("MySQL.password"), getConfig().getBoolean("MySQL.enable"));
        if(mysql.enable()){
            try {
                if(mysql.checkConnection())
                    mysql.openConnection();
                mysql.createTableIfNotExists("Chain", "Player VARCHAR(32), Kills INTEGER, Deaths INTEGER, Points INTEGER");
            } catch (SQLException ex) {
                Logger.getLogger(Chain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Chain.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(mysql.checkConnection())
                        mysql.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(Chain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}

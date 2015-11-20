/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain.api;

import br.crazy.chain.Chain;
import br.crazy.chain.playerdata.PlayerData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;

/**
 *
 * @author Hugo
 */
public class API {
    
    private final Chain main;
    
    private List<PlayerData> data;
    
    public API(Chain m){
        this.main = m;
        this.data = new ArrayList<>();
        this.load();
    }
    
    public void join(Player p){}
    
    public void quit(Player p){}
    
    public void insert(PlayerData pd){
        if(main.getMySQL().enable())
            return;
        if(!data.contains(pd))
            try {
                if(!main.getMySQL().checkConnection())
                    main.getMySQL().openConnection();
                PreparedStatement ps = main.getMySQL().getConnection().prepareStatement("INSERT INTO `Chain`(Player, Kills, Deaths, Points) VALUES (?, ?, ?, ?)");
                ps.execute();
                ps.closeOnCompletion();
                data.add(pd);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(main.getMySQL().checkConnection())
                    main.getMySQL().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void update(PlayerData pd){
        if(main.getMySQL().enable())
            return;
        if(data.contains(pd))
            try {
                if(!main.getMySQL().checkConnection())
                    main.getMySQL().openConnection();
                PreparedStatement ps = main.getMySQL().getConnection().prepareStatement("UPDATE `Chain` SET Kills=?, Deaths=?, Points=? WHERE Player='" + pd.getName() + "'");
                ps.execute();
                ps.closeOnCompletion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(main.getMySQL().checkConnection())
                    main.getMySQL().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void load(){
        if(main.getMySQL().enable())
            return;
        try {
            if(!main.getMySQL().checkConnection())
                main.getMySQL().openConnection();
            ResultSet rs = main.getMySQL().querySQL("SELECT * FROM Chain");
            while(rs.next()){
                PlayerData pd = new PlayerData(rs.getString("Player"));
                pd.updateKills(rs.getInt("Kills"));
                pd.updateDeaths(rs.getInt("Deaths"));
                pd.updatePoints(rs.getInt("Points"));
                data.add(pd);
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(main.getMySQL().checkConnection())
                    main.getMySQL().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
}

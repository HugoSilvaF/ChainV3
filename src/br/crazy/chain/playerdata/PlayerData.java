/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain.playerdata;

/**
 *
 * @author Hugo
 */
public class PlayerData {

    private final String name;

    private int kills;
    private int deaths;
    private int points;

    public PlayerData(String name) {
        this.name = name;
        this.kills = 0;
        this.deaths = 0;
        this.points = 0;
    }
    
    public String getName(){
        return this.name;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public int getPoints() {
        return this.points;
    }

    public void updateKills(int i) {
        this.kills += i;
    }

    public void updateDeaths(int i) {
        this.deaths += i;
    }

    public void updatePoints(int i) {
        this.points += i;
    }

}

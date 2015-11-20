/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain.arena;

import br.crazy.chain.Chain;
import br.crazy.chain.api.API;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Hugo
 */
public class Arena {
    
    private final Chain main;
    private final API api;

    private final String name;

    private final int points_per_kill;
    private final int points_per_death;
    private final int health_per_bowl;
    private final int limit;
    private final boolean limit_enable;

    private List<Player> players;
    private List<Location> locations;
    private List<Location> locations_signs;

    public Arena(String name, Chain m, int points_per_kill, int points_per_death, int health, int limit, boolean bool) {
        this.main = m;
        this.api = m.getAPI();
        this.name = name;
        this.health_per_bowl = health;
        this.limit = limit;
        this.limit_enable = bool;
        this.points_per_kill = 0;
        this.points_per_death = 0;
        this.players = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.locations_signs = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Location> getLocations() {
        return this.locations;
    }

    public List<Location> getLocationsSigns() {
        return this.locations_signs;
    }

    public int getPointsPerKill() {
        return this.points_per_kill;
    }

    public int getPointsPerDeath() {
        return this.points_per_death;
    }

    public int getHealthPerBowl() {
        return this.health_per_bowl;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getPlayersSize() {
        return this.players.size();
    }
    
    public boolean limitEnable(){
        return this.limit_enable;
    }

    public boolean playerHasJoined(Player p) {
        return this.players.contains(p);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

}

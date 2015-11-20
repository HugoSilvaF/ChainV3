/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain.message;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author Hugo
 */
public enum MessageHandler {

    ERROR_ARENA_FULL("messages.arena_full");

    private String path;

    private MessageHandler(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    private List<String> getList(FileConfiguration c) {
        return c.getStringList(getPath());
    }

    public void send(Player p, FileConfiguration c) {
        for(String loop : getList(c))
            p.sendMessage(loop.replace("$player", p.getName()).replaceAll("&", "§"));
    }

}

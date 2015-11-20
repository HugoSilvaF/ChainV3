/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author Hugo
 */
public class Util {

    public static Location toLocation(String s) {
        if (s.contains(" ")) {
            String[] a = s.split(" ");
            return new Location(Bukkit.getWorld(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]));
        }
        return null;
    }

    public static boolean checkInventory(Player p) {
        return checkItemStack(p.getInventory().getArmorContents())
                && checkItemStack(p.getInventory().getContents());
    }

    private static boolean checkItemStack(ItemStack[] array) {
        for (ItemStack is : array) {
            if (is == null) {
                return true;
            }
        }
        return false;
    }

    public static void pasteinventory(Player p) {
        clearinventory(p);
        PlayerInventory i = p.getInventory();
        i.setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        i.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        i.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        i.setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        i.addItem(new ItemStack(Material.IRON_SWORD));
        i.addItem(new ItemStack(Material.BOW));
        i.addItem(new ItemStack(Material.ARROW, 20));
    }

    public static void clearinventory(Player p) {
        PlayerInventory i = p.getInventory();
        i.clear();
        i.setHelmet(new ItemStack(Material.AIR));
        i.setChestplate(new ItemStack(Material.AIR));
        i.setLeggings(new ItemStack(Material.AIR));
        i.setBoots(new ItemStack(Material.AIR));
    }

}

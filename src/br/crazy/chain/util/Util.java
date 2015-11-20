/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.crazy.chain.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Hugo
 */
public class Util {

    public static boolean checkInventory(Player p) {
        return checkItemStack(p.getInventory().getArmorContents())
                && checkItemStack(p.getInventory().getContents());
    }

    private static boolean checkItemStack(ItemStack[] array) {
        for (ItemStack is : array)
            if (is == null) 
                return true;
        return false;
    }

}

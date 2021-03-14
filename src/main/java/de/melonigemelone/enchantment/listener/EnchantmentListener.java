package de.melonigemelone.enchantment.listener;

import de.melonigemelone.enchantment.MelonEnchantment;
import de.melonigemelone.enchantment.handler.EnchantmentHandler;
import de.melonigemelone.enchantment.model.EnchantmentInventory;
import de.melonigemelone.enchantment.model.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class EnchantmentListener implements Listener {

    @EventHandler
    private void onEnchantItemEvent(EnchantItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    private void onPrepareEnchantment(PrepareItemEnchantEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    private void onRightClickEnchantmentTable(PlayerInteractEvent event){
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
           return;
        }
        Block block = event.getClickedBlock();
        if(!block.getType().equals(Material.ENCHANTING_TABLE)) {
            return;
        }

        event.setCancelled(true);
        Player player = event.getPlayer();
        EnchantmentInventory enchantmentInventory = new EnchantmentInventory(player);
        enchantmentInventory.openInventory();
        MelonEnchantment.getEnchantmentHandler().addInventory(player.getUniqueId(), enchantmentInventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getView().getTitle().equals(Message.GUI_TITLE.getMessage()))) {
            return;
        }
        EnchantmentInventory enchantmentInventory = MelonEnchantment.getEnchantmentHandler().getInventory(event.getWhoClicked().getUniqueId());
        enchantmentInventory.checkOnInventoryClick(event, event.getCurrentItem());
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent inventoryCloseEvent) {
        EnchantmentHandler enchantmentHandler = MelonEnchantment.getEnchantmentHandler();
        UUID uuid = inventoryCloseEvent.getPlayer().getUniqueId();
        enchantmentHandler.removeInventory(uuid);
    }

}

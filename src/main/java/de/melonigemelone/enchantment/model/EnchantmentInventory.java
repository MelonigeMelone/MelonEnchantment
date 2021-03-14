package de.melonigemelone.enchantment.model;

import de.melonigemelone.api.lib.minecraft.ItemBuilder;
import de.melonigemelone.api.server.messages.GeneralMessages;
import de.melonigemelone.enchantment.MelonEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantmentInventory {

    private final Player player;
    private ItemStack itemToEnchant;
    private ItemStack additionalItem;
    private ItemStack magicItem;
    private Inventory inventory;

    private final int ITEM_TO_ENCHANT_SLOT = 10;
    private final int ADDITIONAL_ITEM_SLOT = 14;
    private final int MAGIC_ITEM_SLOT = 15;

    public EnchantmentInventory(Player player) {
        this.player = player;
        this.itemToEnchant = null;
        this.additionalItem = null;
        this.magicItem = null;
        this.inventory = createInventory();
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItemToEnchant() {
        return itemToEnchant;
    }

    public void setItemToEnchant(ItemStack itemToEnchant) {
        this.itemToEnchant = itemToEnchant;
        inventory.setItem(ITEM_TO_ENCHANT_SLOT, itemToEnchant);
        reloadBooks();
    }

    public boolean isItemToEnchantSet() {
        return itemToEnchant != null;
    }

    public ItemStack getAdditionalItem() {
        return additionalItem;
    }

    public void setAdditionalItem(ItemStack additionalItem) {
        this.additionalItem = additionalItem;
        inventory.setItem(ADDITIONAL_ITEM_SLOT, additionalItem);
    }

    public boolean isAdditionalItemSet() {
        return additionalItem != null;
    }

    public ItemStack getMagicItem() {
        return magicItem;
    }

    public void setMagicItem(ItemStack magicItem) {
        this.magicItem = magicItem;
        inventory.setItem(MAGIC_ITEM_SLOT, magicItem);
    }

    public boolean isMagicItemSet() {
        return magicItem != null;
    }

    public Inventory createInventory() {
        Inventory inventory = Bukkit.createInventory(player, 54, Message.GUI_TITLE.getMessage());

        ItemStack backGround = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)
                .setName(" ")
                .build();

        ItemStack itemToEnchant = new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE, 1)
                .setName("§bItem zum verzaubern")
                .addLore("§8» §7Das Item, welches du verzaubern willst")
                .build();

        ItemStack additionalItem = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE, 1)
                .setName("§aEffektverstärker")
                .addLore("§8» §7Zusätzliche Items um eine Verzauberung wirksam zu machen")
                .build();

        ItemStack magicItem = new ItemBuilder(Material.YELLOW_STAINED_GLASS_PANE, 1)
                .setName("§eMagische Essenz")
                .addLore("§8» §7Ein Material mit magischen Eigenschaften. (Lapis, Emerald)")
                .build();

        for(int i = 0; i<27; i++) {
            inventory.setItem(i, backGround);
        }

        inventory.setItem(ITEM_TO_ENCHANT_SLOT, itemToEnchant);
        inventory.setItem(ADDITIONAL_ITEM_SLOT, additionalItem);
        inventory.setItem(MAGIC_ITEM_SLOT, magicItem);

        return inventory;
    }

    public void openInventory() {
        player.openInventory(inventory);
    }

    public void reloadBooks() {
        List<EnchantmentData> enchantmentDataList = MelonEnchantment.getEnchantmentHandler().getEnchantmentsForItem(itemToEnchant);
        int slotCounter = 27;
        for(EnchantmentData enchantmentData : enchantmentDataList) {
            inventory.setItem(slotCounter, enchantmentData.getItemStack(itemToEnchant.getEnchantmentLevel(enchantmentData.getEnchantment())));
            slotCounter++;
            if(slotCounter >= 54) {
                return;
            }
        }
    }

    public void checkOnInventoryClick(InventoryClickEvent event, ItemStack itemStack) {
        if(itemStack == null) {
            return;
        }

        if(itemStack.getType().equals(Material.AIR)) {
            return;
        }

        event.setCancelled(true);

        if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            if(ItemType.getItemTypeOfItem(itemStack) != null){
                setItemToEnchant(itemStack);
                return;
            }
           if(itemStack.getType().equals(Material.EMERALD) || itemStack.getType().equals(Material.LAPIS_LAZULI)) {
               setMagicItem(itemStack);
               return;
           }
           setAdditionalItem(itemStack);
            return;
        }

        if(itemStack.getType().equals(Material.ENCHANTED_BOOK)) {
            canEnchantItem(itemStack);
            return;
        }

    }


    public void canEnchantItem(ItemStack itemStack) {
        EnchantmentData enchantmentData = MelonEnchantment.getEnchantmentHandler().getEnchantmentDataFromItemStack(itemStack);
        Enchantment enchantment = enchantmentData.getEnchantment();

        if(itemToEnchant.getEnchantmentLevel(enchantment) >= enchantment.getMaxLevel()) {
            return;
        }

        if(!isMagicItemSet()) {
            player.sendMessage(GeneralMessages.PREFIX.getMessage(false) + "§cDu hast keine oder die falsche Magische Essenz gesetzt!");
            return;
        }

        int enchantmentLevel = itemToEnchant.getEnchantmentLevel(enchantment);
        Material magicItem = enchantmentData.getMagicItemMaterial();
        int magicItemAmount = enchantmentData.getMagicItemCost(enchantmentLevel);
        if(!(this.magicItem.getType().equals(magicItem) && this.magicItem.getAmount() >=magicItemAmount)) {
            player.sendMessage(GeneralMessages.PREFIX.getMessage(false) + "§cDu hast keine oder die falsche Magische Essenz gesetzt!");
            return;
        }

        if(!isAdditionalItemSet()) {
            player.sendMessage(GeneralMessages.PREFIX.getMessage(false) + "§cDu hast keinen oder den falschen Effektverstärker gesetzt!");
            return;
        }

        Material additionalItem = enchantmentData.getAdditionalItemMaterial();
        int additionalItemAmount = enchantmentData.getAdditionalItemCosts(enchantmentLevel);
        if(!(this.additionalItem.getType().equals(additionalItem) && this.additionalItem.getAmount() >= additionalItemAmount)) {
            player.sendMessage(GeneralMessages.PREFIX.getMessage(false) + "§cDu hast keine oder die falsche Magische Essenz gesetzt!");
            return;
        }

        Inventory playerInventory = player.getInventory();
        playerInventory.removeItem(new ItemBuilder(magicItem, magicItemAmount).build());
        playerInventory.removeItem(new ItemBuilder(additionalItem, additionalItemAmount).build());

        enchantmentData.enchantItem(itemToEnchant);
        player.closeInventory();

    }
}

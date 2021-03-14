package de.melonigemelone.enchantment.handler;

import de.melonigemelone.enchantment.handler.config.EnchantmentConfigHandler;
import de.melonigemelone.enchantment.model.EnchantmentData;
import de.melonigemelone.enchantment.model.EnchantmentInventory;
import de.melonigemelone.enchantment.model.ItemType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EnchantmentHandler {

    private final List<EnchantmentData> enchantmentDataList;
    private HashMap<UUID, EnchantmentInventory> playerInventories = new HashMap<>();

    public EnchantmentHandler(EnchantmentConfigHandler enchantmentConfigHandler) {
        enchantmentDataList = enchantmentConfigHandler.loadValues();
    }

    public List<EnchantmentData> getEnchantmentsForItem(ItemStack itemStack) {
        List<EnchantmentData> enchantmentDataList = new ArrayList<>();
        ItemType itemType = ItemType.getItemTypeOfItem(itemStack);
        for(EnchantmentData enchantmentData : this.enchantmentDataList) {
            if(enchantmentData.getItemTypes().contains(itemType)) {
                enchantmentDataList.add(enchantmentData);
            }
        }
        return enchantmentDataList;
    }

    public EnchantmentData getEnchantmentDataFromItemStack(ItemStack itemStack) {
        for(EnchantmentData enchantmentData : this.enchantmentDataList) {
            if(itemStack.getItemMeta().getDisplayName().contains(enchantmentData.getDisplayName())) {
                return enchantmentData;
            }
        }
        return null;
    }

    public void addInventory(UUID uuid, EnchantmentInventory enchantmentInventory) {
        playerInventories.put(uuid, enchantmentInventory);
    }

    public void removeInventory(UUID uuid) {
        playerInventories.remove(uuid);
    }

    public EnchantmentInventory getInventory(UUID uuid) {
        return playerInventories.get(uuid);
    }


}

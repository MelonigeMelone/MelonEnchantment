package de.melonigemelone.enchantment.model;

import de.melonigemelone.api.lib.minecraft.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantmentData {

    private Enchantment enchantment;
    private String displayName;
    private int emeraldCosts;
    private int lapisCosts;
    private Material additionalItemMaterial;
    private int additionalItemCost;
    private List<ItemType> itemTypes;

    public EnchantmentData(Enchantment enchantment, String displayName, int emeraldCosts, int lapisCosts, Material additionalItemMaterial, int additionalItemCost, List<ItemType> itemTypes) {
        this.enchantment = enchantment;
        this.displayName = displayName;
        this.emeraldCosts = emeraldCosts;
        this.lapisCosts = lapisCosts;
        this.additionalItemMaterial = additionalItemMaterial;
        this.additionalItemCost = additionalItemCost;
        this.itemTypes = itemTypes;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDefaultEmeraldCosts() {
        return emeraldCosts;
    }

    public int getEmeraldCosts(int level) {
        if(level == 0) {
            return emeraldCosts;
        }
        return emeraldCosts * level;
    }

    public int getDefaultLapisCosts() {
        return lapisCosts;
    }

    public int getLapisCosts(int level) {
        if(level == 0) {
            return lapisCosts;
        }
        return lapisCosts * level;
    }

    public Material getAdditionalItemMaterial() {
        return additionalItemMaterial;
    }

    public int getDefaultAdditionalItemCost() {
        return additionalItemCost;
    }

    public int getAdditionalItemCosts(int level) {
        if(level == 0) {
            return additionalItemCost;
        }
        return additionalItemCost * level;
    }

    public List<ItemType> getItemTypes() {
        return itemTypes;
    }

    public Material getMagicItemMaterial() {
        if(getDefaultEmeraldCosts() > 0) {
            return Material.EMERALD;
        } else {
            return Material.LAPIS_LAZULI;
        }
    }

    public int getMagicItemCost(int level) {
        if(getDefaultEmeraldCosts() > 0) {
            return getEmeraldCosts(level);
        } else {
            return getLapisCosts(level);
        }
    }

    public boolean hasItemMaxEnchantmentLevel(ItemStack itemStack) {
        return itemStack.getEnchantmentLevel(enchantment) >= enchantment.getMaxLevel();
    }

    public void enchantItem(ItemStack itemStack) {
        int level = itemStack.getEnchantmentLevel(enchantment);
        itemStack.addEnchantment(enchantment, level+1);
    }

    public ItemStack getItemStack(int level) {
        if(level >= enchantment.getMaxLevel()) {
            return new ItemBuilder(Material.ENCHANTED_BOOK, 1)
                    .setName("§e" + displayName + " " + getNumberInRomanFormat(level))
                    .addLore("§cMaximales Level")
                    .build();
        }

        int nextLevel = level +1;
        String magicItemName = getEmeraldCosts(nextLevel) + "x Smaragd";
        if(getLapisCosts(nextLevel) > 0) {
            magicItemName = getLapisCosts(nextLevel) + "x Lapis";
        }
        return new ItemBuilder(Material.ENCHANTED_BOOK, 1)
                .setName("§e" + displayName +  " " + getNumberInRomanFormat(nextLevel))
                .addLore("§bMagische Essenz: " + magicItemName)
                .addLore("§aItem Verstärker: " + additionalItemCost + "x " + additionalItemMaterial.name())
                .build();
    }

    public String getNumberInRomanFormat(int level) {
        switch (level) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
        }
        return "";
    }

}

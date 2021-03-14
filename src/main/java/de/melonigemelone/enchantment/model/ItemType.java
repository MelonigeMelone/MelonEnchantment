package de.melonigemelone.enchantment.model;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ItemType {
    BOOTS,LEGGINGS,CHESTPLATE,HELMET, SWORD, BOW, PICKAXE, SHOVEL, AXE, HOE, SCISSORS, FISHING_ROD, SHIELD, TRIDENT, CROSSBOW;


    public static List<ItemType> getItemTypesFromStringList(List<String> stringValues) {
        List<ItemType> values = new ArrayList<>();
        for(String s : stringValues) {
            values.add(valueOf(s));
        }
        return values;
    }

    public static List<String> getItemTypesToStringList() {
        List<String> values = new ArrayList<>();
        for(ItemType itemType : values()) {
            values.add(itemType.name());
        }
        return values;
    }

    public static ItemType getItemTypeOfItem(ItemStack itemStack){
        Material type = itemStack.getType();
        if(type.equals(Material.TURTLE_HELMET) || type.equals(Material.NETHERITE_HELMET) || type.equals(Material.DIAMOND_HELMET) || type.equals(Material.CHAINMAIL_HELMET) || type.equals(Material.IRON_HELMET) || type.equals(Material.GOLDEN_HELMET)){
            return ItemType.HELMET;
        }
        else if(type.equals(Material.NETHERITE_LEGGINGS) || type.equals(Material.DIAMOND_LEGGINGS) || type.equals(Material.CHAINMAIL_LEGGINGS) || type.equals(Material.IRON_LEGGINGS) || type.equals(Material.GOLDEN_LEGGINGS)){
            return ItemType.LEGGINGS;
        }
        else if(type.equals(Material.NETHERITE_CHESTPLATE) || type.equals(Material.DIAMOND_CHESTPLATE) || type.equals(Material.CHAINMAIL_CHESTPLATE) || type.equals(Material.IRON_CHESTPLATE) || type.equals(Material.GOLDEN_CHESTPLATE)){
            return ItemType.CHESTPLATE;
        }
        else if(type.equals(Material.NETHERITE_BOOTS) || type.equals(Material.DIAMOND_BOOTS) || type.equals(Material.CHAINMAIL_BOOTS) || type.equals(Material.IRON_BOOTS) || type.equals(Material.GOLDEN_BOOTS)){
            return ItemType.BOOTS;
        }
        else if(type.equals(Material.WOODEN_SWORD) || type.equals(Material.STONE_SWORD) || type.equals(Material.GOLDEN_SWORD) || type.equals(Material.IRON_SWORD) || type.equals(Material.DIAMOND_SWORD) || type.equals(Material.NETHERITE_SWORD)){
            return ItemType.SWORD;
        }
        else if(type.equals(Material.WOODEN_AXE) || type.equals(Material.STONE_AXE) || type.equals(Material.GOLDEN_AXE) || type.equals(Material.IRON_AXE) || type.equals(Material.DIAMOND_AXE) || type.equals(Material.NETHERITE_AXE)){
            return ItemType.AXE;
        }
        else if(type.equals(Material.WOODEN_SHOVEL) || type.equals(Material.STONE_SHOVEL) || type.equals(Material.GOLDEN_SHOVEL) || type.equals(Material.IRON_SHOVEL) || type.equals(Material.DIAMOND_SHOVEL) || type.equals(Material.NETHERITE_SHOVEL)){
            return ItemType.SHOVEL;
        }
        else if(type.equals(Material.WOODEN_HOE) || type.equals(Material.STONE_HOE) || type.equals(Material.GOLDEN_HOE) || type.equals(Material.IRON_HOE) || type.equals(Material.DIAMOND_HOE) || type.equals(Material.NETHERITE_HOE)){
            return ItemType.HOE;
        }
        else if(type.equals(Material.WOODEN_PICKAXE) || type.equals(Material.STONE_PICKAXE) || type.equals(Material.GOLDEN_PICKAXE) || type.equals(Material.IRON_PICKAXE) || type.equals(Material.DIAMOND_PICKAXE) || type.equals(Material.NETHERITE_PICKAXE)){
            return ItemType.PICKAXE;
        }
        else if(type.equals(Material.SHEARS)){
            return ItemType.SCISSORS;
        }
        else if(type.equals(Material.FISHING_ROD)){
            return ItemType.FISHING_ROD;
        }
        else if(type.equals(Material.SHIELD)){
            return ItemType.SHIELD;
        }
        else if(type.equals(Material.BOW)){
            return ItemType.BOW;
        }
        else if(type.equals(Material.CROSSBOW)){
            return ItemType.CROSSBOW;
        }
        else if(type.equals(Material.TRIDENT)){
            return ItemType.TRIDENT;
        }
        return null;
    }
}

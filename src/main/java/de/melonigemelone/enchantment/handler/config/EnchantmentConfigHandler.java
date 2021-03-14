package de.melonigemelone.enchantment.handler.config;

import de.melonigemelone.api.lib.configuration.yaml.YamlFileBuilder;
import de.melonigemelone.enchantment.MelonEnchantment;
import de.melonigemelone.enchantment.model.EnchantmentData;
import de.melonigemelone.enchantment.model.ItemType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentConfigHandler extends YamlFileBuilder {

    public EnchantmentConfigHandler() {
        super(MelonEnchantment.getInstance().getDataFolder().toString(), "enchantments.yml");
        setDefaultValues();
    }

    public void setDefaultValues() {
        for(Enchantment enchantment : Enchantment.values()) {
            setIfNotExists("Enchantment."+ enchantment.getKey().getKey() + ".DisplayName", enchantment.getKey().getKey());
            setIfNotExists("Enchantment."+ enchantment.getKey().getKey() + ".EmeraldCosts", 2);
            setIfNotExists("Enchantment."+ enchantment.getKey().getKey() + ".LapisCosts", 0);
            setIfNotExists("Enchantment."+ enchantment.getKey().getKey() + ".AdditionalItemMaterial", "DIRT");
            setIfNotExists("Enchantment."+ enchantment.getKey().getKey() + ".AdditionalItemCosts", 0);
            setIfNotExists("Enchantment."+ enchantment.getKey().getKey() + ".AvailableFor", ItemType.getItemTypesToStringList());
        }
        save();
    }

    public List<EnchantmentData> loadValues() {
        List<EnchantmentData> enchantmentData = new ArrayList<>();

        for(Enchantment enchantment : Enchantment.values()) {
            enchantmentData.add(new EnchantmentData(
                    enchantment,
                    getString("Enchantment."+enchantment.getKey().getKey() + ".DisplayName"),
                    getInt("Enchantment."+ enchantment.getKey().getKey() + ".EmeraldCosts"),
                    getInt("Enchantment."+ enchantment.getKey().getKey() + ".LapisCosts"),
                    Material.getMaterial(getString("Enchantment."+ enchantment.getKey().getKey() + ".AdditionalItemMaterial")),
                    getInt("Enchantment."+ enchantment.getKey().getKey() + ".AvailableFor"),
                    ItemType.getItemTypesFromStringList(getStringList("Enchantment."+ enchantment.getKey().getKey() + ".AvailableFor"))
            ));
        }

        return enchantmentData;
    }
}

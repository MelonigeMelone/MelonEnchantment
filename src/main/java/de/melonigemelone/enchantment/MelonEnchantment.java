package de.melonigemelone.enchantment;

import de.melonigemelone.enchantment.handler.EnchantmentHandler;
import de.melonigemelone.enchantment.handler.config.EnchantmentConfigHandler;
import de.melonigemelone.enchantment.listener.EnchantmentListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MelonEnchantment extends JavaPlugin {

    private static MelonEnchantment instance;

    private static EnchantmentConfigHandler enchantmentConfigHandler;
    private static EnchantmentHandler enchantmentHandler;

    @Override
    public void onEnable() {
        instance = this;

        enchantmentConfigHandler = new EnchantmentConfigHandler();
        enchantmentHandler = new EnchantmentHandler(enchantmentConfigHandler);

        this.getServer().getPluginManager().registerEvents(new EnchantmentListener(),this);


    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static MelonEnchantment getInstance() {
        return instance;
    }

    public static EnchantmentHandler getEnchantmentHandler() {
        return enchantmentHandler;
    }
}

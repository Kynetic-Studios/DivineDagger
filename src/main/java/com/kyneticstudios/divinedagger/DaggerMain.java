package com.kyneticstudios.divinedagger;

import co.aikar.commands.BukkitCommandManager;
import com.kyneticstudios.divinedagger.command.DDCommand;
import com.kyneticstudios.divinedagger.item.DaggerItem;
import com.kyneticstudios.divinedagger.listener.DDListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DaggerMain extends JavaPlugin {

    private final DaggerItem daggerItem = new DaggerItem();

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        BukkitCommandManager bm = new BukkitCommandManager(this);

        //command & event resgister
        bm.registerCommand(new DDCommand(daggerItem));
        pm.registerEvents(new DDListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

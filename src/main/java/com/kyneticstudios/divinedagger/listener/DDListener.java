package com.kyneticstudios.divinedagger.listener;

import com.kyneticstudios.divinedagger.DaggerMain;
import com.kyneticstudios.divinedagger.item.DaggerItem;
import com.kyneticstudios.divinedagger.utils.TextUtil;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DDListener implements Listener {

    private final Server server;
    private  DaggerItem daggerItem;

    public DDListener() {
        this.server = Bukkit.getServer();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (event.getEntity() instanceof Player) {
            Player vicim = (Player) event.getEntity();
            Player killer =  (Player) event.getEntity().getKiller();

            ItemStack hand = killer.getInventory().getItemInHand();
            NBTItem nbtItem = new NBTItem(hand);

            if (hand == null || !nbtItem.hasKey(DaggerItem.NBT_KEY)) {
                return;
            }else {
                event.setDeathMessage(null);
            }
        }
    }

    @EventHandler
    public void onDaggerUse(EntityDamageByEntityEvent event) {


        Player damager = (Player) event.getDamager();
        Player damaged = (Player) event.getEntity();

        //fixed so it doesnt work in pvp zones
        if (event.isCancelled()) return;

        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player))
            event.setCancelled(true);


        ItemStack hand = damager.getInventory().getItemInHand();
        NBTItem nbtItem = new NBTItem(hand);

        if (hand == null || !nbtItem.hasKey(DaggerItem.NBT_KEY)) {
            return;
        }

        event.setDamage(10000.0);

        damager.getInventory().setItemInHand(null);
        damager.updateInventory();

        server.broadcastMessage(TextUtil.center(TextUtil.translate("&5" + damager.getDisplayName() + " &7has killed &5" + damaged.getDisplayName())));
        server.broadcastMessage(TextUtil.center(TextUtil.translate("&eusing " + daggerItem.name)));
    }
}
package com.kyneticstudios.divinedagger.item;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static com.kyneticstudios.divinedagger.utils.TextUtil.translate;


public class DaggerItem {
    public static final String NBT_KEY = "divine_dagger";
    public final String name;

    public DaggerItem() {
        this.name = translate("&5&lDivine Dagger");
    }


    public ItemStack getDaggerItem() {
        ItemStack dd = new ItemStack(Material.GOLD_SWORD);
        ItemMeta meta = dd.getItemMeta();

        meta.setDisplayName(this.name);
        meta.setLore(Arrays.asList(
                translate("&dA divine dagger with the"),
                translate("&dability to one shot anyone,"),
                translate("&dbut gets destroyed on use.")
        ));
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        dd.setDurability((short) 1);

        dd.setItemMeta(meta);

        NBTItem nbt = new NBTItem(dd);
        nbt.setBoolean(NBT_KEY, true);

        return nbt.getItem();
    }

}

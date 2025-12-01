package com.kyneticstudios.divinedagger.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.kyneticstudios.divinedagger.item.DaggerItem;
import com.kyneticstudios.divinedagger.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("DD|DivineDagger")
public class DDCommand extends BaseCommand {

    private final DaggerItem dagger;
    private final String perm = "core.admin";

    public DDCommand(DaggerItem dagger) {
        this.dagger = dagger;
    }


    @HelpCommand
    public void commandHelp(CommandSender sender) {
        sender.sendMessage(TextUtil.translate("&dUsage: &f/DD give [player]"));
    }

    @Subcommand("give")
    @Description("Give Divine Dagger to yourself or another player")
    public void onGive(CommandSender sender, @Optional Player targetName) {

        if (!(sender instanceof Player) && targetName == null) {
            sender.sendMessage(TextUtil.translate("&cOnly players can give themselves items."));
            return;
        }

        if (!sender.hasPermission(perm)) {
            sender.sendMessage(TextUtil.translate("&cYou do not have permission to do this."));
            return;
        }

        Player target = (Player) targetName;

        if (targetName == null) {
            target = (Player) sender;
        } else {
            target = Bukkit.getPlayerExact(targetName.getName());

            if (target == null) {
                sender.sendMessage(TextUtil.translate("&cPlayer not found."));
                return;
            }
        }

        target.getInventory().addItem(dagger.getDaggerItem());
        target.sendMessage(TextUtil.translate("&aYou have been given the Divine Dagger!"));

        if (!target.equals(sender)) {
            sender.sendMessage(TextUtil.translate("&aYou gave &e" + target.getName() + " &athe Divine Dagger."));
        }
    }
}


package com.github.seagiga.oceanfps.command;

import com.github.seagiga.oceanfps.listener.EntityPacket;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class FPSCommand extends Command {

    public static final Set<String> playerSet = new HashSet<>();
    public FPSCommand() {
        super("fps");
    }
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;
        if (playerSet.contains(player.getName())){
            EntityPacket.returnLocal(player, player.getLocation());
            playerSet.remove(player.getName());
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            return true;
        }
        EntityPacket.returnLocal(player, player.getLocation());
        playerSet.add(player.getName());
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        return false;
    }
}
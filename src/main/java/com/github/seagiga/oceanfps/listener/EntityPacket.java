package com.github.seagiga.oceanfps.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.nbt.NbtBase;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.github.seagiga.oceanfps.OceanFPS;
import com.github.seagiga.oceanfps.command.FPSCommand;
import com.github.seagiga.oceanfps.util.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class EntityPacket extends PacketAdapter {
    public EntityPacket(Plugin plugin, PacketType... types) {
        super(plugin, types);
    }

    public void onPacketSending(PacketEvent event) {
        if (event.isPlayerTemporary()) return;
        Player player = event.getPlayer();
        PacketContainer packet = event.getPacket();
        if (player == null) return;
        if (!FPSCommand.playerSet.contains(player.getName())) return;
        if (((Integer) packet.getIntegers().read(0)).intValue() != 1) return;
        NbtCompound nbt = (NbtCompound) packet.getNbtModifier().read(0);
        if (nbt.getKeys().contains("EntityId")) nbt.put("EntityId", "null");
        if (nbt.getKeys().contains("SpawnData"))
            nbt.put("SpawnData", (NbtBase) nbt.getCompound("SpawnData").put("id", "null"));
        nbt.put("RequiredPlayerRange", (short) 0);
    }

    public static void returnLocal(Player player, Location location) {
        new Title().title("§aSucesso").subTitle("§aRetornará em 5 segundos.").fadeIn(20).fadeOut(20).stay(60).send(new Player[]{player});
        Bukkit.getScheduler().scheduleSyncDelayedTask(OceanFPS.get(), new Runnable() {
            @Override
            public void run() {
                player.teleport(location);
            }
        }, 100L);
    }
}
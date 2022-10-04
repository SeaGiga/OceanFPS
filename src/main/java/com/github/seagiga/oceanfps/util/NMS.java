package com.github.seagiga.oceanfps.util;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMS {
    public static void sendPacket(Player player, Packet packet) {
        if (packet == null)
            return;
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket(packet);
    }
}

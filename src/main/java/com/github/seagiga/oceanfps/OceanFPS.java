package com.github.seagiga.oceanfps;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.github.seagiga.oceanfps.listener.EntityPacket;
import com.github.seagiga.oceanfps.manager.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OceanFPS extends JavaPlugin {

    @Override
    public void onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new EntityPacket(this, PacketType.Play.Server.TILE_ENTITY_DATA));
        new CommandManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static OceanFPS get(){return OceanFPS.getPlugin(OceanFPS.class);}
}



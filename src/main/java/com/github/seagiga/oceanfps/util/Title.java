package com.github.seagiga.oceanfps.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Title {
    private String title = ChatColor.RESET.toString();

    public String getTitle() {
        return this.title;
    }

    private String subTitle = ChatColor.RESET.toString();

    private int fadeIn;

    private int fadeOut;

    public String getSubTitle() {
        return this.subTitle;
    }

    public int getFadeIn() {
        return this.fadeIn;
    }

    public int getFadeOut() {
        return this.fadeOut;
    }

    private int stay = 1;

    public int getStay() {
        return this.stay;
    }

    private boolean translateColorCodes = true;

    public boolean isTranslateColorCodes() {
        return this.translateColorCodes;
    }

    public Title title(String title) {
        this.title = this.translateColorCodes ? ChatColor.translateAlternateColorCodes('&', title) : title;
        return this;
    }

    public Title subTitle(String subTitle) {
        this.subTitle = this.translateColorCodes ? ChatColor.translateAlternateColorCodes('&', subTitle) : subTitle;
        return this;
    }

    public Title fadeIn(int ticks) {
        this.fadeIn = ticks;
        return this;
    }

    public Title stay(int ticks) {
        this.stay = ticks;
        return this;
    }

    public Title fadeOut(int ticks) {
        this.fadeOut = ticks;
        return this;
    }

    public Title doNotTranslateColorCodes() {
        this.translateColorCodes = false;
        return this;
    }

    public void send(Player... players) {
        List<PacketPlayOutTitle> packets = Lists.newLinkedList();
        if (this.fadeIn > 0 || this.stay > 0 || this.fadeOut > 0) {
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (IChatBaseComponent) null, this.fadeIn, this.stay, this.fadeOut);
            packets.add(packetPlayOutTitle);
        }
        PacketPlayOutTitle wrapper = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, (IChatBaseComponent)new ChatComponentText(this.title));
        packets.add(wrapper);
        wrapper = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, (IChatBaseComponent)new ChatComponentText(this.subTitle));
        packets.add(wrapper);
        for (Player player : players) {
            for (PacketPlayOutTitle packetPlayOutTitle : packets)
                NMS.sendPacket(player, (Packet)packetPlayOutTitle);
        }
    }

    public void send() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(new Player[] { player });
        }
    }

    public static void clear(Player... players) {
        PacketPlayOutTitle wrapper = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, null);
        for (Player player : players)
            NMS.sendPacket(player, (Packet)wrapper);
    }
}
package me.marenji.util;

import org.bukkit.ChatColor;

public class ChatHelper {

    public static String chat(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}

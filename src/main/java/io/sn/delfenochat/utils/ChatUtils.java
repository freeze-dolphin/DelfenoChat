package io.sn.delfenochat.utils;

import net.kyori.adventure.text.minimessage.MiniMessage;

public class ChatUtils {

    public static String processChatMessage(String text) {
        if (text.startsWith("$")) {
            return MiniMessage.miniMessage().deserialize(
                    "<rainbow>" + text.substring(1) + "</rainbow>").toString();
        } else if (text.startsWith("@")) {
            return text.substring(1);
        } else {
            return "{#74ebd5>}" + text + "{#acb6e5<}";
        }
    }
}

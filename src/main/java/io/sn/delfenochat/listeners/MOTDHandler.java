package io.sn.delfenochat.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import io.sn.delfenochat.utils.ChatUtils;
import net.kyori.adventure.text.Component;

public class MOTDHandler {

    /*
    ░▒█▀▀▄░█▀▀░█░░█▀▀░█▀▀░█▀▀▄░▄▀▀▄
    ░▒█░▒█░█▀▀░█░░█▀░░█▀▀░█░▒█░█░░█
    ░▒█▄▄█░▀▀▀░▀▀░▀░░░▀▀▀░▀░░▀░░▀▀░
     */

    private Component minides(String text) {
        return ChatUtils.minides(text);
    }

    @Subscribe
    public void onJoin(LoginEvent evt) {
        Component motd = minides("<newline><st>                                           <newline><newline>")
                .append(minides("<gradient:green:blue>░▒█▀▀▄░█▀▀░█░░█▀▀░█▀▀░█▀▀▄░▄▀▀▄</gradient><newline>"))
                .append(minides("<gradient:green:blue>░▒█░▒█░█▀▀░█░░█▀░░█▀▀░█░▒█░█░░█</gradient><newline>"))
                .append(minides("<gradient:green:blue>░▒█▄▄█░▀▀▀░▀▀░▀░░░▀▀▀░▀░░▀░░▀▀░</gradient><newline><newline>"))
                .append(minides(
                        "    <rainbow>Chat Reinforcer</rainbow><newline>" +
                                "    <rainbow:!2>Composed by Freeze_Dolphin</rainbow><newline>" +
                                "    <rainbow:2>Powered by MiniChat</rainbow> <dark_gray>[<gray>AdventureApi<dark_gray>]<newline>"))
                .append(minides("<newline><st>                                           <newline>"))
                .append(minides("<newline>" +
                        " <gold><bold>食用方法:<newline>" +
                        " <gold> - <yellow>默认聊天是渐变彩色的<newline>" +
                        "          <yellow>以英文逗号 (,) 开头的文本是无颜色的<newline>" +
                        "    <red>(需要 'delfeno.chat.use' 权限)<newline>" +
                        " <gold> - <yellow>以美元符号 ($) 开头的文本是彩虹的<newline>" +
                        "    <red>(需要 'delfeno.chat.use' 权限)<newline>" +
                        "    <red><bold>此功能会被当作作弊踢出服务器<newline>" +
                        " <gold> - <yellow>以双波浪线 (~~) 开头的文本是该拼音的成语<newline>" +
                        "    <red>(需要 'delfeno.chat.cy' 权限)<newline>" +
                        " <gold> - <yellow>双冒号文本会被替换成颜文字<newline>" +
                        "    <red>(需要 'delfeno.chat.moe' 权限)<newline><newline>" +
                        "    <white>目前可用的颜文字:<newline>"))
                .append(ChatUtils.concludeMoeDict());

        evt.getPlayer().sendMessage(motd);
    }

}

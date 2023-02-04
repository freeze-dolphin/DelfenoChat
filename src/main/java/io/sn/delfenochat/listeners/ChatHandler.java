package io.sn.delfenochat.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import io.sn.delfenochat.utils.ChatUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.IOException;

public class ChatHandler {

    @Subscribe
    public void onChat(PlayerChatEvent evt) {
        String rst = evt.getMessage();
        if (evt.getMessage().matches("~~.*") && evt.getPlayer().hasPermission("delfeno.chat.cy")) {
            try {
                rst = ChatUtils.matchChengYu(rst.substring(2));
            } catch (IOException | BadHanyuPinyinOutputFormatCombination e) {
                evt.getPlayer().showTitle(Title.title(Component.text("无法解析成语"), Component.text("请检查你的首字母拼音是否正确")));
                evt.setResult(PlayerChatEvent.ChatResult.denied());
            }
        } else {
            if (evt.getPlayer().hasPermission("delfeno.chat.use")) {
                rst = ChatUtils.processChatMessage(rst);
            }
            if (evt.getPlayer().hasPermission("delfeno.chat.moe")) {
                rst = ChatUtils.processMoe(rst);
            }
        }
        evt.setResult(PlayerChatEvent.ChatResult.message(rst));
    }

    @Subscribe
    public void onCommand(CommandExecuteEvent evt) {
        if (evt.getCommand().startsWith("msg ")
                || evt.getCommand().startsWith("tell ")) {
            if (evt.getCommandSource().hasPermission("delfeno.chat.use")) {
                String[] args = evt.getCommand().split(" ");
                String target = args[1];
                StringBuilder sb = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                evt.setResult(CommandExecuteEvent.CommandResult.forwardToServer("msg " + target + " " + ChatUtils.processChatMessage(sb.toString())));
            }
        }
    }

    private static String lastUsername = "";

    @Subscribe
    public void prevMsg(CommandExecuteEvent evt) {
        if (evt.getCommand().startsWith("msg ")) {
            lastUsername = evt.getCommand().split(" ")[1];
        } else if (evt.getCommand().startsWith("r ")) {
            evt.setResult(CommandExecuteEvent.CommandResult.forwardToServer(
                    "msg " + lastUsername + " " + ChatUtils.processChatMessage(
                            evt.getCommand().substring(2))));
        }
    }

}

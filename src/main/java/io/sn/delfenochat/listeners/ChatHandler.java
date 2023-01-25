package io.sn.delfenochat.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import io.sn.delfenochat.utils.ChatUtils;

public class ChatHandler {

    @Subscribe
    public void onChat(PlayerChatEvent evt) {
        if (evt.getPlayer().hasPermission("delfeno.chat.use")) {
            evt.setResult(PlayerChatEvent.ChatResult.message(ChatUtils.processChatMessage(evt.getMessage())));
        }
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

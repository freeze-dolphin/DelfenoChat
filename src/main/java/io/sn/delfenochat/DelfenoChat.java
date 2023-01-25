package io.sn.delfenochat;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import io.sn.delfenochat.listeners.ChatHandler;
import org.slf4j.Logger;

@Plugin(
        id = "delfenochat",
        name = "DelfenoChat",
        version = "0.1.0",
        authors = {"Freeze_Dolphin"}
)
public class DelfenoChat {

    @Inject
    private Logger logger;

    @Inject
    private ProxyServer server;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new ChatHandler());
    }
}

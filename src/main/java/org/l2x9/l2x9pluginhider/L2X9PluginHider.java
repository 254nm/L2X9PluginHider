package org.l2x9.l2x9pluginhider;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.l2x9.l2x9pluginhider.listeners.CommandEvent;
import org.l2x9.l2x9pluginhider.listeners.TabPacket;

public final class L2X9PluginHider extends JavaPlugin {
    private static L2X9PluginHider instance;
    private ProtocolManager protocolManager;
    private final PluginManager PLUGIN_MANAGER = getServer().getPluginManager();

    @Override
    public void onEnable() {
        if (instance == null) instance = this;
        if (protocolManager == null) protocolManager = ProtocolLibrary.getProtocolManager();
        saveDefaultConfig();
        protocolManager.addPacketListener(new TabPacket());
        PLUGIN_MANAGER.registerEvents(new CommandEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static L2X9PluginHider getInstance() {
        return instance;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}

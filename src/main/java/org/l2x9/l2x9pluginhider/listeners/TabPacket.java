package org.l2x9.l2x9pluginhider.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TabPacket extends PacketAdapter {
    public TabPacket() {
        super(
                L2X9PluginHider.getInstance(),
                ListenerPriority.NORMAL,
                PacketType.Play.Client.TAB_COMPLETE
        );
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        String command = packet.getStrings().read(0);
        if (command.equals("/")) {
            event.setCancelled(true);
            L2X9PluginHider.getInstance().getLogger().log(Level.INFO, "Prevented " + event.getPlayer().getName() + " from seeing the plugins (.pl)");
            if (Constants.SEND_FAKE_PLUGINS) {
                L2X9PluginHider instance = L2X9PluginHider.getInstance();
                Player player = event.getPlayer();
                PacketContainer response = new PacketContainer(PacketType.Play.Server.TAB_COMPLETE);
                response.getStringArrays().write(0, formatPlugins());
                try {
                    instance.getProtocolManager().sendServerPacket(player, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String[] formatPlugins() {
        List<String> plugins = Constants.FAKE_PLUGINS_LIST;
        List<String> formatted = new ArrayList<>();
        for (String plugin : plugins) {
            formatted.add("/" + plugin.concat(":lol"));
        }
        return formatted.toArray(new String[0]);
    }
}

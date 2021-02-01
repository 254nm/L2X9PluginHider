package org.l2x9.l2x9pluginhider.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.l2x9.l2x9pluginhider.util.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class CommandEvent implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (Constants.DISABLE_QUESTION && !player.isOp()) {
            if (event.getMessage().toLowerCase().contains("/?")) {
                L2X9PluginHider.getInstance().getLogger().log(Level.INFO, "Prevented " + event.getPlayer().getName() + " from seeing the plugins (/?)");
                player.sendMessage(ChatColor.RED + "Please use /help instead");
                event.setCancelled(true);
            }
        }
        if (Constants.DISABLE_COLON && !player.isOp()) {
            if (event.getMessage().contains(":")) {
                L2X9PluginHider.getInstance().getLogger().log(Level.INFO, "Prevented " + event.getPlayer().getName() + " from seeing the plugins (/bukkit:help)");
                player.sendMessage(ChatColor.RED + "That syntax is not accepted");
                event.setCancelled(true);
            }
        }
        if (Constants.FAKE_COMMANDS && !player.isOp()) {
            List<String> disabled = Arrays.asList("/plugins", "/pl");
            String command = event.getMessage();
            if (command.length() == disabled.get(0).length()) {
                command = command.substring(0, disabled.get(0).length());
            } else {
                if (command.length() == disabled.get(1).length()) {
                    command = command.substring(0, disabled.get(1).length());
                }
            }
            if (disabled.contains(command.toLowerCase())) {
                String message = ChatColor.translateAlternateColorCodes('&', formatPlugins());
                event.getPlayer().sendMessage(message);
                L2X9PluginHider.getInstance().getLogger().log(Level.INFO, "Prevented " + event.getPlayer().getName() + " from seeing the plugins (/pl)");
                event.setCancelled(true);
            }
        }
    }
    private String formatPlugins() {
        List<String> fakePlugins = Constants.FAKE_PLUGINS_LIST;
        StringBuilder stringBuilder = new StringBuilder();
        String base = "Plugins (" + fakePlugins.size() + "): ";
        for (String plugin : fakePlugins) {
            stringBuilder.append("&a").append(plugin).append("&r, ");
        }
        String message = base + stringBuilder.toString();
        return message.substring(0, message.length() - 2); //Substring to get rid of the last comma
    }
}

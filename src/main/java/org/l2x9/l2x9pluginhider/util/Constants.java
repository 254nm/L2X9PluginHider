package org.l2x9.l2x9pluginhider.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.l2x9.l2x9pluginhider.L2X9PluginHider;

import java.util.List;

public class Constants {
    private static final FileConfiguration CONFIG = L2X9PluginHider.getInstance().getConfig();
    public static final boolean SEND_FAKE_PLUGINS = CONFIG.getBoolean("FakePlugins.SendFakePluginsList");
    public static final List<String> FAKE_PLUGINS_LIST = CONFIG.getStringList("FakePlugins.FakePluginsList");
    public static final boolean FAKE_COMMANDS = CONFIG.getBoolean("FakeCommand");
    public static final boolean DISABLE_QUESTION = CONFIG.getBoolean("Disable?");
    public static final boolean DISABLE_COLON = CONFIG.getBoolean("DisableColon");
}

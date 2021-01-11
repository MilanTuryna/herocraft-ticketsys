package cz.MilanT.herocraft.ticketsys;

import cz.MilanT.herocraft.ticketsys.constants.Variables;
import cz.MilanT.herocraft.ticketsys.settings.Options;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {
    public static char ALT_COLOR_CODE = '&';

    private final Plugin plugin;
    private final File file;
    private final FileConfiguration fileConfiguration;

    private Options options;
    private String prefix;

    public static String colorString(String string) {
        return ChatColor.translateAlternateColorCodes(Configuration.ALT_COLOR_CODE, string);
    }

    public Configuration(Plugin plugin, String fileName) throws IOException, InvalidConfigurationException {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), fileName);
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(file.getName(), false);
        }
        this.fileConfiguration = new YamlConfiguration();
        this.fileConfiguration.load(file);
        this.prefix = Configuration.colorString(fileConfiguration.getString("prefix"));
        this.options = this.generateOptions();
    }

    public void save() {
        try {
            this.fileConfiguration.save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void reloadConfig() throws IOException, InvalidConfigurationException {
        this.fileConfiguration.load(this.file);
        // change separately 'this.prefix' because it's instanced only in constructor
        this.prefix = Configuration.colorString(fileConfiguration.getString("prefix"));
        this.options = this.generateOptions();
    }

    public String getString(String node) {
        return Configuration.colorString(this.getFileConfiguration().getString(node)
                .replace(Variables.PREFIX, this.prefix));
    }

    public Connection getConnection() throws SQLException {
        String host = this.getString("storage.mysql.host");
        String db = this.getString("storage.mysql.db");
        String name = this.getString("storage.mysql.name");
        String password = this.getString("storage.mysql.password");

        return DriverManager.getConnection( "jdbc:mysql://" + host + "/" + db, name, password);
    }

    private Options generateOptions() {
        return new Options(this.getFileConfiguration().getBoolean("options.login_server"));
    }

    public String getMessage(String msg) {
        return this.getString("messages." + msg);
    }

    public Options getOptions() {
        return options;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public String getPrefix() {
        return prefix;
    }
}

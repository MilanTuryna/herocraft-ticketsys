package cz.MilanT.herocraft.ticketsys;

import cz.MilanT.herocraft.ticketsys.bukkit.commands.ReportCommand;
import cz.MilanT.herocraft.ticketsys.bukkit.listeners.PlayerListener;
import cz.MilanT.herocraft.ticketsys.repositories.TicketRepository;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Index extends JavaPlugin {
    public Configuration configuration;
    public TicketRepository ticketRepository;

    private void stopPlugin(String message) {
        this.customLogger(message);
        this.getPluginLoader().disablePlugin(this);
    }

    private void registerCommands() {
        ReportCommand reportCommand = new ReportCommand(this, this.ticketRepository, this.configuration);
        this.getServer().getPluginCommand("report").setExecutor(reportCommand);
    }

    private void registerListeners() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        PlayerListener playerListener = new PlayerListener(this, this.configuration, this.ticketRepository);
        pluginManager.registerEvents(playerListener, this);
    }

    private void init() throws IOException, InvalidConfigurationException, SQLException {
        this.configuration = new Configuration(this, "config.yml");
        Connection connection = this.configuration.getConnection();
        this.ticketRepository = new TicketRepository(configuration, connection);
    }

    @Override
    public void onEnable() {
        this.getLogger().info("Plugin byl uspesne zapnut");
        try {
            this.init();
            this.registerListeners();
            this.registerCommands();
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
            this.stopPlugin("§cPri nacitani konfiguracniho souboru doslo k chybe... vice v horni chybe.");
        } catch (SQLException exception) {
            exception.printStackTrace();;
            this.stopPlugin("§cPri pripojovani do databaze doslo k chybe... viz. horni chyba");
        }
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin byl uspesne vypnut");
    }

    public void customLogger(String message) {
        this.getServer().getConsoleSender().sendMessage(message);
    }
}

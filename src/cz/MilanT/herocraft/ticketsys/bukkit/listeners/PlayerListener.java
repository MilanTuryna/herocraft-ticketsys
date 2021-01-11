package cz.MilanT.herocraft.ticketsys.bukkit.listeners;

import cz.MilanT.herocraft.ticketsys.Configuration;
import cz.MilanT.herocraft.ticketsys.repositories.TicketRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerListener implements Listener {
    private final Plugin plugin;
    private final Configuration configuration;
    private final TicketRepository ticketRepository;

    public PlayerListener(Plugin plugin, Configuration configuration, TicketRepository ticketRepository) {
        this.plugin = plugin;
        this.configuration = configuration;
        this.ticketRepository = ticketRepository;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        if(configuration.getOptions().login_server) {
            playerJoinEvent.getPlayer().sendMessage("Nacitani tvych ticketu..");
            // TODO: sleep method and use ticket list
        }
    }
}

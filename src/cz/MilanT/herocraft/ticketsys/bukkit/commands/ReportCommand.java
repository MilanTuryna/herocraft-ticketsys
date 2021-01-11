package cz.MilanT.herocraft.ticketsys.bukkit.commands;

import cz.MilanT.herocraft.ticketsys.Configuration;
import cz.MilanT.herocraft.ticketsys.repositories.TicketRepository;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ReportCommand implements CommandExecutor {
    private final Plugin plugin;
    private final TicketRepository ticketRepository;
    private final Configuration configuration;

    public ReportCommand(Plugin plugin, TicketRepository ticketRepository, Configuration configuration) {
        this.plugin = plugin;
        this.ticketRepository = ticketRepository;
        this.configuration = configuration;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}

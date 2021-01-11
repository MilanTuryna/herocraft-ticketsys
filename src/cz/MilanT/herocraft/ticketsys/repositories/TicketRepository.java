package cz.MilanT.herocraft.ticketsys.repositories;

import cz.MilanT.herocraft.ticketsys.Configuration;

import java.sql.Connection;

public class TicketRepository {
    public static String TABLE_TICKETS = "tickets";
    public static String TABLE_RESPONSES = "ticket_responses";

    private final Configuration configuration;
    private final Connection connection;

    public TicketRepository(Configuration configuration, Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Connection getConnection() {
        return connection;
    }
}

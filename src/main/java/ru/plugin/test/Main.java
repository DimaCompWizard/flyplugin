package ru.plugin.test;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.security.PermissionCollection;


public final class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable()
    {
        this.getLogger().info("I was successfully started. Congratulations ☺");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!cmd.getName().equals("fly"))
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player!");
            return false;
        }
        Player player = (Player) sender;
        if (player.getAllowFlight()) {
            player.sendMessage("Fly was turned off");
            player.setAllowFlight(false);
        } else {
            player.sendMessage("Fly was turned on");
            player.setAllowFlight(true);
        }
        return true;
    }

//    @EventHandler
//    public void normalLogin(PlayerLoginEvent event) {
//        Player player = event.getPlayer();
//        if (player.hasPermission("flyplugin.fly"))
//            player.setAllowFlight(true);
//    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("flyplugin.fly")) {
            player.setAllowFlight(true);
            this.getLogger().info("he has perms");
        }
        else {
            player.setAllowFlight(false);
            this.getLogger().info("he hasn't perms");
        }
    }

//    @EventHandler
//    public void onPlayerQuit(PlayerQuitEvent event) {
//        event.getPlayer().setAllowFlight(false);
//    }
}

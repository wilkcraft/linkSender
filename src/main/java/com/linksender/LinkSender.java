package com.linksender;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LinkSender extends JavaPlugin {

  private final HashMap<String, String> links = new HashMap<>();

  private File linksFile;
  private FileConfiguration linksConfig;

  @Override
  public void onEnable() {

    createLinksFile();
    loadLinks();

    PluginCommand command = getCommand("link");
    if (command != null) {
      command.setExecutor(this);
      command.setTabCompleter(this);
    }

    getLogger().info("Link-Sender enabled");
  }

  @Override
  public void onDisable() {
    saveLinks();
  }

  // Create file
  private void createLinksFile() {

    linksFile = new File(getDataFolder(), "links.yml");

    if (!linksFile.exists()) {
      linksFile.getParentFile().mkdirs();
      saveResource("links.yml", false);
    }

    linksConfig = YamlConfiguration.loadConfiguration(linksFile);
  }

  // Load links
  private void loadLinks() {

    links.clear();

    if (linksConfig.getConfigurationSection("links") == null)
      return;

    for (String key : linksConfig.getConfigurationSection("links").getKeys(false)) {

      String url = linksConfig.getString("links." + key);
      links.put(key.toLowerCase(), url);
    }
  }

  // Save links
  private void saveLinks() {

    linksConfig.set("links", null);

    for (String key : links.keySet()) {
      linksConfig.set("links." + key, links.get(key));
    }

    try {
      linksConfig.save(linksFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Reload
  private void reloadLinks() {

    linksConfig = YamlConfiguration.loadConfiguration(linksFile);
    loadLinks();
  }

  // Styled message
  private Component createLinkMessage(String name, String url) {

    return Component.text("🔗 ")
        .append(Component.text(name, NamedTextColor.AQUA))
        .append(Component.text(" | Click to open", NamedTextColor.GRAY))
        .clickEvent(ClickEvent.openUrl(url));
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!(sender instanceof Player player)) {
      sender.sendMessage("Only players can use this.");
      return true;
    }

    if (args.length == 0) {
      player.sendMessage("§7Usage:");
      player.sendMessage("§e/link create <name> <url>");
      player.sendMessage("§e/link delete <name>");
      player.sendMessage("§e/link list");
      player.sendMessage("§e/link reload");
      return true;
    }

    switch (args[0].toLowerCase()) {

      case "create":

        if (!player.hasPermission("link.create")) {
          player.sendMessage("§cYou don't have permission.");
          return true;
        }

        if (args.length < 3) {
          player.sendMessage("§eUsage: /link create <name> <url>");
          return true;
        }

        String name = args[1].toLowerCase();
        String url = args[2];

        links.put(name, url);
        saveLinks();

        player.sendMessage("§aLink '" + name + "' created.");
        return true;

      case "delete":

        if (!player.hasPermission("link.delete")) {
          player.sendMessage("§cYou don't have permission.");
          return true;
        }

        if (args.length < 2) {
          player.sendMessage("§eUsage: /link delete <name>");
          return true;
        }

        String delete = args[1].toLowerCase();

        if (!links.containsKey(delete)) {
          player.sendMessage("§cThat link does not exist.");
          return true;
        }

        links.remove(delete);
        saveLinks();

        player.sendMessage("§aLink deleted.");
        return true;

      case "list":

        if (!player.hasPermission("link.list")) {
          player.sendMessage("§cYou don't have permission.");
          return true;
        }

        player.sendMessage("§7Available links:");

        for (String key : links.keySet()) {
          player.sendMessage("§e- " + key);
        }

        return true;

      case "reload":

        if (!player.hasPermission("link.reload")) {
          player.sendMessage("§cYou don't have permission.");
          return true;
        }

        reloadLinks();

        player.sendMessage("§aLinks reloaded.");
        return true;
    }

    String name = args[0].toLowerCase();

    if (!links.containsKey(name)) {
      player.sendMessage("§cThat link does not exist.");
      return true;
    }

    String url = links.get(name);
    Component message = createLinkMessage(name, url);

    if (args.length == 1) {

      if (!player.hasPermission("link.me")) {
        player.sendMessage("§cYou don't have permission.");
        return true;
      }

      player.sendMessage(message);
      return true;
    }

    if (args.length == 2) {

      if (!player.hasPermission("link.send")) {
        player.sendMessage("§cOnly OPs can send links.");
        return true;
      }

      Player target = Bukkit.getPlayer(args[1]);

      if (target == null) {
        player.sendMessage("§cPlayer not found.");
        return true;
      }

      target.sendMessage(message);
      player.sendMessage("§aLink sent to " + target.getName());
    }

    return true;
  }

  // TAB COMPLETION
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

    List<String> completions = new ArrayList<>();

    if (args.length == 1) {

      if (sender.hasPermission("link.create"))
        completions.add("create");

      if (sender.hasPermission("link.delete"))
        completions.add("delete");

      if (sender.hasPermission("link.list"))
        completions.add("list");

      if (sender.hasPermission("link.reload"))
        completions.add("reload");

      completions.addAll(links.keySet());
    }

    if (args.length == 2) {

      if (args[0].equalsIgnoreCase("create"))
        completions.add("<name>");

      if (args[0].equalsIgnoreCase("delete"))
        completions.addAll(links.keySet());

      if (links.containsKey(args[0])) {
        for (Player p : Bukkit.getOnlinePlayers()) {
          completions.add(p.getName());
        }
      }
    }

    if (args.length == 3 && args[0].equalsIgnoreCase("create")) {
      completions.add("<url>");
    }

    return completions;
  }
}

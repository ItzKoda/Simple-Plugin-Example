/*    */ package drewthorn.joinandleave;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public final class Joinandleave
/*    */   extends JavaPlugin
/*    */   implements Listener {
/*    */   public void onEnable() {
/* 18 */     System.out.println(ChatColor.AQUA + "========================");
/* 19 */     System.out.println(ChatColor.AQUA + "[" + ChatColor.WHITE + "Celestial join and leave" + ChatColor.AQUA + "] " + ChatColor.WHITE + "has started!");
/* 20 */     System.out.println(ChatColor.AQUA + "========================");
/*    */ 
/*    */     
/* 23 */     getServer().getPluginManager().registerEvents(this, (Plugin)this);
/*    */ 
/*    */     
/* 26 */     getConfig().options().copyDefaults();
/* 27 */     getConfig().addDefault("on-player-join", "&b[&fCelestial&b] &fWelcome Back &b%player%");
/* 28 */     getConfig().addDefault("first-time-player-join", "&b[&fCelestial&b] &fWelcome &b%player%");
/* 29 */     getConfig().addDefault("on-player-join-special", "&b[&fCelestial&b] &fA Very Special Welcome Back To &b%player%");
/* 30 */     getConfig().addDefault("on-player-leave", "&b[&fCelestial&b] &fHope &b%player% &fHad a good time");
/* 31 */     getConfig().addDefault("on-player-quit-special", "&b[&fCelestial&b] &fA Very Special GoodBye To &b%player%");
/* 32 */     saveDefaultConfig();
/*    */ 
/*    */     
/* 35 */     Logger logger = getLogger();
/*    */     
/* 37 */     (new UpdateChecker(this, 82202)).getVersion(version -> {
/*    */           if (getDescription().getVersion().equalsIgnoreCase("1.0.1")) {
/*    */             logger.info(ChatColor.AQUA + "[" + ChatColor.WHITE + "Celestial Join/Leave" + ChatColor.AQUA + "]" + ChatColor.RED + " There is not a new update available.");
/*    */           } else {
/*    */             logger.info(ChatColor.AQUA + "[" + ChatColor.WHITE + "Celestial Join/Leave" + ChatColor.AQUA + "]" + ChatColor.RED + " There is a new update available.");
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 51 */     System.out.println(ChatColor.AQUA + "========================");
/* 52 */     System.out.println(ChatColor.AQUA + "[" + ChatColor.WHITE + "Celestial join and leave" + ChatColor.AQUA + "] " + ChatColor.WHITE + "has stopped!");
/* 53 */     System.out.println(ChatColor.AQUA + "========================");
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onJoin(PlayerJoinEvent event) {
/* 59 */     Player player = event.getPlayer();
/* 60 */     int firstJoinCounter = 1;
/*    */     
/* 62 */     String onJoin = getConfig().getString("on-player-join").replaceAll("%player%", player.getName());
/* 63 */     String firstTimeJoin = getConfig().getString("first-time-player-join").replaceAll("%player%", player.getName()).replaceAll("%PlayerCounter%", Integer.toString(firstJoinCounter));
/* 64 */     String onJoinSpecial = getConfig().getString("on-player-join-special").replaceAll("%player%", player.getName());
/* 65 */     if (player.hasPermission("celestialjoin.silent")) {
/* 66 */       event.setJoinMessage("");
/* 67 */     } else if (player.hasPermission("celestialjoin.specialjoin")) {
/* 68 */       event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', onJoinSpecial));
/* 69 */     } else if (player.hasPermission("celestialjoin.join")) {
/* 70 */       if (player.hasPlayedBefore()) {
/* 71 */         event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', onJoin));
/*    */       } else {
/* 73 */         event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', firstTimeJoin));
/* 74 */         firstJoinCounter++;
/*    */       } 
/*    */     } else {
/* 77 */       Bukkit.broadcastMessage(ChatColor.RED + "No Join Message Permission has been set");
/*    */     } 
/*    */   }
/*    */   @EventHandler
/*    */   public void onLeave(PlayerQuitEvent event) {
/* 82 */     Player player = event.getPlayer();
/*    */     
/* 84 */     String onQuit = getConfig().getString("on-player-leave").replaceAll("%player%", player.getName());
/* 85 */     String onQuitSpecial = getConfig().getString("on-player-quit-special").replaceAll("%player%", player.getName());
/* 86 */     if (player.hasPermission("celestialquit.silent")) {
/* 87 */       event.setQuitMessage("");
/* 88 */     } else if (player.hasPermission("celestialquit.specialquit")) {
/* 89 */       event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', onQuitSpecial));
/* 90 */     } else if (player.hasPermission("celestialquit.quit")) {
/* 91 */       event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', onQuit));
/*    */     } else {
/* 93 */       Bukkit.broadcastMessage(ChatColor.RED + "No Leave Message Permission has been set");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\drewt\Desktop\Celestial_JoinLeave_1.0.1.jar!\drewthorn\joinandleave\Joinandleave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
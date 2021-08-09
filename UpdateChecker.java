/*    */ package drewthorn.joinandleave;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import java.util.Scanner;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import org.bukkit.util.Consumer;
/*    */ 
/*    */ 
/*    */ public class UpdateChecker
/*    */ {
/*    */   private JavaPlugin plugin;
/*    */   private int resourceId;
/*    */   
/*    */   public UpdateChecker(JavaPlugin plugin, int resourceId) {
/* 19 */     this.plugin = plugin;
/* 20 */     this.resourceId = resourceId;
/*    */   }
/*    */   
/*    */   public void getVersion(Consumer<String> consumer) {
/* 24 */     Bukkit.getScheduler().runTaskAsynchronously((Plugin)this.plugin, () -> { try { InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId)).openStream(); try { Scanner scanner = new Scanner(inputStream); try { if (scanner.hasNext())
/* 25 */                   consumer.accept(scanner.next());  scanner.close(); } catch (Throwable throwable) { try { scanner.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  if (inputStream != null) inputStream.close();  } catch (Throwable throwable) { if (inputStream != null) try { inputStream.close(); } catch (Throwable throwable1)
/*    */                 { throwable.addSuppressed(throwable1); }
/*    */                   throw throwable; }
/*    */              }
/* 29 */           catch (IOException exception)
/*    */           { this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage()); }
/*    */         
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\drewt\Desktop\Celestial_JoinLeave_1.0.1.jar!\drewthorn\joinandleave\UpdateChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
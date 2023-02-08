package pl.robalmeister.goxy.chat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.goxy.minecraft.api.GoxyApi;
import pl.robalmeister.goxy.chat.GoxyChatPlugin;
import pl.robalmeister.goxy.chat.chat.ChatData;
import pl.robalmeister.goxy.chat.helper.LuckPermsHelper;
import pl.robalmeister.goxy.chat.pubsub.PubSub;

import java.util.Objects;

public class ChatListener implements Listener {
    private final PubSub pubSub;

    public ChatListener(PubSub pubSub) {
        this.pubSub = pubSub;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String server;
        try {
            server = Objects.requireNonNull(GoxyApi.getNetworkManager().getServer()).getName();
        } catch (Exception ignored) {
            server = "";
        }
        ChatData chatData = new ChatData(e.getPlayer().getName(), LuckPermsHelper.getPrefix(e.getPlayer()),
                e.getPlayer().hasPermission("chat.colors"), e.getMessage(), server);
        pubSub.subscribe(chatData);
        JavaPlugin.getPlugin(GoxyChatPlugin.class).send(chatData);
        e.setCancelled(true);
    }
}

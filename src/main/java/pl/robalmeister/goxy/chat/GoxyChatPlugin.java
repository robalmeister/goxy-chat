package pl.robalmeister.goxy.chat;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import pl.goxy.minecraft.pubsub.PubSubService;
import pl.robalmeister.goxy.chat.chat.ChatData;
import pl.robalmeister.goxy.chat.config.Configuration;
import pl.robalmeister.goxy.chat.pubsub.PubSub;
import pl.robalmeister.goxy.chat.pubsub.redis.RedisPubsub;

import java.io.File;
import java.util.Objects;

public class GoxyChatPlugin extends JavaPlugin {
    private Object goxyPubSub;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Configuration configuration = new Configuration(getConfig(), new File(getDataFolder(), "config.yml"));
        PubSub pubSub = new PubSub(BukkitAudiences.create(this), configuration);
        if (getServer().getPluginManager().isPluginEnabled("goxy-pubsub")) {
            PubSubService pubSubService = (PubSubService) Objects.requireNonNull(
                    getServer().getPluginManager().getPlugin("goxy-pubsub"));
            goxyPubSub = pubSubService.getPubSub(this);
            ((pl.goxy.minecraft.pubsub.PubSub) goxyPubSub).registerHandler("chat", ChatData.class, new RedisPubsub(pubSub));
        }
    }

    public void send(ChatData chatData) {
        if (goxyPubSub == null) return;
        ((pl.goxy.minecraft.pubsub.PubSub) goxyPubSub).sendPluginNetwork("chat", chatData);
    }
}

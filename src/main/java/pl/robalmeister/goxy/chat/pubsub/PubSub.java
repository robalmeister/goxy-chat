package pl.robalmeister.goxy.chat.pubsub;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import pl.robalmeister.goxy.chat.chat.ChatData;
import pl.robalmeister.goxy.chat.config.Configuration;
import pl.robalmeister.goxy.chat.helper.ChatHelper;
import pl.robalmeister.goxy.chat.helper.LuckPermsHelper;

public class PubSub {
    private final BukkitAudiences audiences;
    private final Configuration configuration;

    public PubSub(BukkitAudiences audiences, Configuration configuration) {
        this.audiences = audiences;
        this.configuration = configuration;
    }

    public void subscribe(ChatData chatData) {
        String configString = configuration.getString("chat.message");
        User user = LuckPermsProvider.get().getUserManager().getUser(chatData.getNickname());

        String message = configString
                .replace("[PLAYER]", chatData.getNickname())
                .replace("[PREFIX]", (configuration.getBoolean("chat.prefix") ? chatData.getPrefix() : (user == null ? "" : LuckPermsHelper.getPrefix(user))))
                .replace("[SERVER]", chatData.getServer())
                .replace("&", "§")
                .replace("[MESSAGE]", chatData.getMessage());

        audiences.all().sendMessage(ChatHelper.parse(message.replace("&", (chatData.hasPermission() ? "§" : "&"))));

    }
}

package pl.robalmeister.goxy.chat.helper;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class LuckPermsHelper {
    private LuckPermsHelper() {
        throw new IllegalStateException("Utility class!");
    }

    @NotNull
    public static String getPrefix(Group group) {
        String prefix = group.getCachedData().getMetaData().getPrefix();
        return (prefix == null ? group.getName() : prefix);
    }

    @NotNull
    public static String getPrefix(String group) {
        return getPrefix(Optional.ofNullable(LuckPermsProvider.get().getGroupManager().getGroup(group)).orElseThrow());
    }


    @NotNull
    public static String getPrefix(User user) {
        return getPrefix(user.getPrimaryGroup());
    }

    @NotNull
    public static String getPrefix(Player player) {
        return getPrefix(Optional.ofNullable(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId())).orElseThrow());
    }
}

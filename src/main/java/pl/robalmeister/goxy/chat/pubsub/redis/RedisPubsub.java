package pl.robalmeister.goxy.chat.pubsub.redis;

import pl.goxy.minecraft.api.GoxyApi;
import pl.goxy.minecraft.pubsub.PubSubContext;
import pl.goxy.minecraft.pubsub.PubSubDataHandler;
import pl.robalmeister.goxy.chat.chat.ChatData;
import pl.robalmeister.goxy.chat.pubsub.PubSub;

import java.util.Objects;

public class RedisPubsub implements PubSubDataHandler<ChatData> {
    private final PubSub pubSub;

    public RedisPubsub(PubSub pubSub) {
        this.pubSub = pubSub;
    }

    @Override
    public void accept(PubSubContext pubSubContext, ChatData chatData) {
        if (Objects.equals(pubSubContext.getServer().getId(), Objects.requireNonNull(GoxyApi.getNetworkManager().getServer()).getId())) {
            return;
        }
        pubSub.subscribe(chatData);
    }
}

package pl.robalmeister.goxy.chat.helper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;
import java.util.stream.Collectors;

public class ChatHelper {

    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.builder()
            .hexColors()
            .character('§')
            .hexCharacter('#')
            .useUnusualXRepeatedCharacterHexFormat()
            .extractUrls().build();

    private ChatHelper() {
        throw new IllegalStateException("Utility class!");
    }

    public static Component parse(String text) {
        return SERIALIZER.deserialize(text
                .replace(">>", "»")
                .replace("<<", "«")
        );
    }

    public static List<Component> parse(List<String> text) {
        return text.stream().map(ChatHelper::parse).collect(Collectors.toList());
    }

    public static String serialize(Component text) {
        return SERIALIZER.serialize(text);
    }

    public static List<String> serialize(List<Component> text) {
        return text.stream().map(ChatHelper::serialize).collect(Collectors.toList());
    }
}


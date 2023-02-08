package pl.robalmeister.goxy.chat.chat;

public class ChatData {
    private String nickname;
    private String prefix;
    private boolean hasPermission;
    private String message;
    private String server;

    public ChatData(String nickname, String prefix, boolean hasPermission, String message, String server) {
        this.nickname = nickname;
        this.prefix = prefix;
        this.hasPermission = hasPermission;
        this.message = message;
        this.server = server;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean hasPermission() {
        return hasPermission;
    }

    public String getServer() {
        return server;
    }

    public String getMessage() {
        return message;
    }
}

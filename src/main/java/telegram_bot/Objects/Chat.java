package telegram_bot.Objects;

public class Chat {
    private String name;
    private String id;

    public Chat (String name, String id){
        this.name = name;
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


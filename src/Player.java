public class Player {
    private char type;
    private String name;

    public Player(char type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public char getType() {
        return type;
    }
}


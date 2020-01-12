public class Player {
    String name;
    public Player(String name) {
        this.name = name;
    }
    static int roll() {
        return (int) Math.ceil(Math.random() * 6);
    }
    public String getName() {
        return this.name;
    }
}
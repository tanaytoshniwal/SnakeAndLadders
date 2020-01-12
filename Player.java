public class Player {
    String name;
    int position;
    static int[] dieValues = {1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6};
    public Player(String name) {
        this.name = name;
        this.position = 0;
    }
    static int roll() {
        return dieValues[((int) Math.ceil(Math.random() * dieValues.length)) - 1];
    }
    public String getName() {
        return this.name;
    }
    public int getPosition() {
        return this.position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
import java.util.*;
public class GameBoard {
    
    private int SIZE = 10;
    private int SNAKES = 7;
    private int LADDERS = 6;
    private int[][] board;
    private int[][] snakes;
    private int[][] ladders;
    private HashMap<Player, Integer> currentPosition;

    private void initializeBoard() {
        board = new int[SIZE][SIZE];

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                board[i][j] = i*SIZE+j+1; 
            }
        }
        snakes = new int[SNAKES][2];
        snakes[0][0] = 28;
        snakes[0][1] = 10;
        snakes[1][0] = 37;
        snakes[1][1] = 3;
        snakes[2][0] = 47;
        snakes[2][1] = 16;
        snakes[3][0] = 75;
        snakes[3][1] = 32;
        snakes[4][0] = 94;
        snakes[4][1] = 71;
        snakes[5][0] = 96;
        snakes[5][1] = 42;
        snakes[6][0] = 99;
        snakes[6][1] = 6;

        ladders = new int[LADDERS][2];
        ladders[0][0] = 22;
        ladders[0][1] = 58;
        ladders[1][0] = 4;
        ladders[1][1] = 56;
        ladders[2][0] = 14;
        ladders[2][1] = 55;
        ladders[3][0] = 12;
        ladders[3][1] = 50;
        ladders[4][0] = 41;
        ladders[4][1] = 79;
        ladders[5][0] = 54;
        ladders[5][1] = 88;
    }

    public GameBoard(ArrayList<Player> players) {

        currentPosition = new HashMap<>();

        for(Player p: players){
            currentPosition.put(p, 0);
        }

        initializeBoard();
    }

    public boolean play(Player player, int rollValue) {
        int curPos = this.currentPosition.get(player);
        curPos += rollValue;
        if(curPos >= 100) {
            currentPosition.put(player, 100);
            return true;
        }
        for(int snake = 0;snake<SNAKES;snake++) {
            if(curPos == snakes[snake][0]){
                System.out.println("Snake bit " + player.getName() + "!");
                currentPosition.put(player, snakes[snake][1]);
                return false;
            }
        }
        for(int ladder = 0;ladder<LADDERS;ladder++) {
            if(curPos == ladders[ladder][0]){
                System.out.println(player.getName() + " climbed a ladder!");
                currentPosition.put(player, ladders[ladder][1]);
                return false;
            }
        }
        currentPosition.put(player, curPos);
        return false;
    }

    public StringBuffer printBoard() {
        boolean flag = true;
        StringBuffer printableString = new StringBuffer("");
        for(int i=SIZE-1;i>=0;i--){
            for(int j=0;j<SIZE;j++){
                if(flag){
                    String row = "";
                    boolean empty = true;
                    for(Player player: currentPosition.keySet()){
                        if(board[i][SIZE-j-1] == currentPosition.get(player)){
                            empty = false;
                            row += player.getName().charAt(0) + " ";
                        }
                    }
                    if(!empty) printableString.append(row + "\t");
                    else printableString.append(board[i][SIZE-j-1] + "\t");
                }
                else {
                    boolean empty = true;
                    String row = "";
                    for(Player player: currentPosition.keySet()) {
                        if(board[i][j] == currentPosition.get(player)) {
                            empty = false;
                            row += player.getName().charAt(0) + " ";
                        }
                    }
                    if(!empty) printableString.append(row + "\t");
                    else printableString.append(board[i][j] + "\t");
                }
            }
            flag = !flag;
            printableString.append("\n");
        }
        return printableString;
    }
}
import java.util.*;
class InvalidInputException extends Exception {
    public InvalidInputException(String msg){
        super(msg);
    }
}
public class SnakeLadder {

    static int numPlayers;
    static ArrayList<Player> players;
    static Scanner sc = new Scanner(System.in);

    public static void inputPlayersNumber() {
        try {
            numPlayers = sc.nextInt();
            if(numPlayers<2 || numPlayers>4)
                throw new InvalidInputException("Input a valid number(2-4): ");
        }
        catch(InvalidInputException e) {
            System.out.print(e.getMessage());
            inputPlayersNumber();
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter number of players: ");
        inputPlayersNumber();
        System.out.println("Input number is: " + numPlayers);
        players = new ArrayList<>();
        for(int i=0;i<numPlayers;i++){
            System.out.print("Input player(" + (i+1) + ") name: ");
            String name = sc.next();
            players.add(new Player(name));
        }

        GameBoard board = new GameBoard(players);

        boolean finished = false;
        int curPlayer = 0;
        while(!finished) {
            System.out.print("Input (y/Y) to roll: ");
            String temp = sc.next();
            if(temp.equals("y") || temp.equals("Y")){
                Player currentPlayer = players.get(curPlayer);
                int rollValue = currentPlayer.roll();
                System.out.println(currentPlayer.getName() + " got " + rollValue + "!");
                finished = board.play(currentPlayer, rollValue);
                System.out.println(board.printBoard());
                System.out.println("-------------------------------------------------------------------");
                if(finished) {
                    System.out.println("Player " + currentPlayer.getName() + " wins the game!!!");
                }
                if(++curPlayer == numPlayers){
                    curPlayer = 0;
                }
            }
        }
    }
}
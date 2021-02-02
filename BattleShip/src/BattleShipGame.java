import java.util.Scanner;
public class BattleShipGame {
    // board
    public static String [][] board(){
        String [][] board = new String[12][14];
        for (int row = 0; row<board.length; row++) {
            for (int column = 0; column <board[row].length;column++) {
                if ((row == 0 || row == 11) && column >= 2 && column <= 11 )
                    board[row][column] = Integer.toString(column-2);
                else if ((column == 0 || column == 13) && 1 <= row && row <= 10 )
                    board[row][column] = Integer.toString( row - 1);
                else if ((column == 1 || column == 12) && 1 <= row && row <= 10 )
                    board[row][column] ="|";
                else
                    board[row][column] = " ";
            }
        }
        return board;
    }

    //print board
    public static void print(String [][] board) {
        for (String[] strings : board) {
            for (int column = 0; column < strings.length; column++) {
                System.out.print(strings[column]);
                if (column == 13)
                    System.out.println();
            }
        }
        System.out.println();
    }
    // user deploy
    public static void UserDeploy(String [][] board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deploy your ships: ");
        for (int i = 1; i <= 5 ; i ++) {
            boolean unchecked = true;
            while(unchecked) {
                System.out.print("Enter X coordinate for your "+ i+"."+ " ship:");
                int x = scanner.nextInt() + 1;
                System.out.print("Enter Y coordinate for your "+ i+"."+ " ship:");
                int y = scanner.nextInt() + 2;
                if (( 2<=y && y<=11) && (1<=x && x<= 10)) {
                    if(board[x][y].equals(" ")) {
                        board[x][y] = "@";
                        print(board);
                        unchecked = false;
                    }
                    else
                        System.out.println("You already put those numbers");
                }
                else
                    System.out.println("The boundary is 0 to 10");
            }
        }
    }
    public static void ComputerDeploy(String [][] userBoard, String[][] computerBoard) {
        System.out.println("Computer is deploying ships");
        for (int i = 1; i<=5; i++) {
            boolean unchecked = true;
            while (unchecked) {
                int x = (int)(Math.random()*10) +1;
                int y = (int)(Math.random()*10) +2;
                if (userBoard[x][y].equals(" ") && !computerBoard[x][y].equals("@")) {
                    computerBoard[x][y] = "@";
                    System.out.println(i + ". ship DEPLOYED");
                    unchecked = false;
                }
            }
        }
        System.out.println("--------------------------------------------");
    }
    public static void PlayerTurn(String [][] userBoard, String[][] computerBoard) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("YOUR TURN");
        boolean unchecked = true;
        while (unchecked) {
            System.out.print("Enter X coordinate: ");
            int x = scanner.nextInt() + 1 ;
            System.out.print("Enter Y coordinate: ");
            int y = scanner.nextInt() + 2 ;

            if (( 2<=y && y<=11) && (1<=x && x<= 10)) {
                if (computerBoard[x][y].equals("@") && userBoard[x][y].equals(" ")) {
                    System.out.println("Boom! You sunk the ship!");
                    userBoard[x][y] = "!";
                    unchecked = false;
                }
                else if (userBoard[x][y].equals("@")) {
                    System.out.println("Oh no, you sunk your own ship :(");
                    userBoard[x][y] = "x";
                    unchecked = false;
                }
                else if (userBoard[x][y].equals(" ") && computerBoard[x][y].equals(" ")) {
                    System.out.println("Sorry, you missed");
                    userBoard[x][y] = "-";
                    unchecked = false;
                }
                else {
                    System.out.println("You already put entered it");
                }
            }
            else {
                System.out.println("The boundary is 0 to 10");
            }
        }
    }
    public static void ComputerTurn(String [][] userBoard, String[][] computerBoard) {
        System.out.println("COMPUTER'S TURN");
        boolean unchecked = true;
        while (unchecked) {
            int x = (int)(Math.random()*10) +1;
            int y = (int)(Math.random()*10) +2;
            if (userBoard[x][y].equals("@") && computerBoard[x][y].equals(" ")) {
                System.out.println("The Computer sunk one of your ships!");
                userBoard[x][y] = "x";
                unchecked = false;
            }
            else if (computerBoard[x][y].equals("@")) {
                System.out.println("The Computer sunk one of its own ships");
                userBoard[x][y] = "!";
                unchecked = false;
            }
            else if (computerBoard[x][y].equals(" ") && userBoard[x][y].equals(" ")) {
                System.out.println("Computer missed");
                computerBoard[x][y] = "-";
                unchecked = false;
            }
        }
    }
    public static String Counting (String [][] userBoard, String[][] computerBoard) {
        int userBoat = 0;
        int computerBoat = 0;
        for (int x = 1 ; x<=10; x++) {
            for (int y = 2; y<=11; y++) {
                if (userBoard[x][y].equals("@"))
                    userBoat++;
                if (computerBoard[x][y].equals("@"))
                    computerBoat ++;
            }
        }
        System.out.println("Your ships: " + userBoat+ " | Computer ships: " + computerBoat);

        if (userBoat == 0) {
            return "Computer won";
        }
        else if (computerBoat == 0) {
            return "User Won";
        }

        return "continue" ;

    }

    public static void main(String[] args) {
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println();
        System.out.println("Right now, the sea is empty");
        System.out.println();
        String[][] userBoard = board();
        String[][] computerBoard = board();
        print(userBoard);
        UserDeploy(userBoard);
        System.out.println();
        ComputerDeploy(userBoard,computerBoard);
        boolean playing = true;
        while (playing) {
            PlayerTurn(userBoard,computerBoard);
            System.out.println();
            ComputerTurn(userBoard,computerBoard);
            print(userBoard);
            String result = Counting(userBoard,computerBoard);
            if (result.equals("User Won")) {
                System.out.println("Hooray! You win the battle :)");
                playing = false;
            }
            else if (result.equals("Computer won")) {
                System.out.println("You Lost. :(");
                playing = false;
            }
        }
    }
}

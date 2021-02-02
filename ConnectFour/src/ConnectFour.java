import java.util.Scanner;

public class ConnectFour {

    // making board
    public static String[][] Board() {
        String [][] board = new String[7][15];
        for (int row = 0; row < board.length;row++) {
            for (int column = 0; column < board[row].length; column++ ) {
                if (column % 2== 0)
                    board[row][column] = "|";
                else
                    board[row][column] = " ";
                if (row == 6 )
                    board[row][column] ="-";
            }
        }
        return board;
    }

    //printing board
    public static void printBoard(String [][] board) {
        for (int row = 0; row < board.length;row++) {
            for (int column = 0; column < board[row].length; column++ ) {
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    //red
    static int turn = 0;
    public static void Turn(String [][] board) {
        Scanner scanner = new Scanner (System.in);
        if (turn % 2 == 0) {
            System.out.print("Drop a red disk at column (0-6): ");
            int redInput = scanner.nextInt();
            int redPlace = 2*(redInput) +1;
            for (int row = 5; row >= -1; row--) {
                if (row == -1) {
                    System.out.println("Already full, choose another column.");
                    break;
                }
                else {
                    if (board[row][redPlace] == " ") {
                        board[row][redPlace] = "R";
                        turn ++;
                        break;
                    }
                }
            }
        }

        else {
            System.out.print("Drop a yellow disk at column (0-6): ");
            int yellowInput = scanner.nextInt();
            int yellowPlace = 2*(yellowInput) +1;
            for (int row = 5; row >= -1; row--) {
                if (row == -1) {
                    System.out.println("already full, choose another column.");
                    break;
                }
                else {
                    if (board[row][yellowPlace] == " ") {
                        board[row][yellowPlace] = "Y";
                        turn ++;
                        break;
                    }
                }
            }
        }
    }

    // deciding winner
    public static String check (String [][] board) {
        //horizontal
        for (int row = 5; row >= 0; row --) {
            for ( int column = 1; column <= (board[row].length)/2; column += 2 ) {
                if ((board[row][column] != " ") &&
                        (board[row][column] == board[row][column+2]) &&
                        (board[row][column+2] == board[row][column+4]) &&
                        (board[row][column+4] == board[row][column+6]))
                    return board[row][column] ;

            }
        }

        //vertical
        for ( int column = 1 ; column < board[0].length; column += 2) {
            for (int row = 0; row < board.length /2 ; row ++) {
                if ((board[row][column]!= " ") &&
                        (board[row][column] == board[row+1][column])     &&
                        (board[row+1][column] == board[row+2][column])   &&
                        (board[row+2][column]== board[row+3][column]))
                    return board[row][column];


            }
        }

        //diagonal(left top to right bottom)
        for (int column = 1; column <= (board[0].length)/2; column +=2) {
            for (int row = 0; row < board.length/2;row ++) {
                if ((board[row][column] != " ") &&
                        (board[row][column] == board[row+1][column+2])   &&
                        (board[row][column] == board[row+2][column+4])   &&
                        (board[row][column] == board[row+3][column+6]))
                    return board[row][column];

            }
        }

        //diagonal(right top to left bottom)
        for (int column = 13 ; column >= (board[0].length)/2; column -= 2) {
            for (int row = 0; row < board.length/2; row ++) {
                if ((board[row][column] != " ") &&
                        (board[row][column] == board[row+1][column-2] ) &&
                        (board[row][column] == board[row+2][column-4])  &&
                        (board[row][column] == board[row+3][column-6]))
                    return board[row][column];
            }
        }

        //draw
        int spaceCount = 0;
        for (int row = 0; row < 6; row ++) {
            for (int column = 1; column < board[row].length; column += 2) {
                if (board[row][column] == " ") {
                    spaceCount += 1;
                }
            }
        }
        if (spaceCount == 0) {
            return "draw";
        }


        return "continue";

    }


    //main
    public static void main(String[] args) {

        String [][] board = Board();
        System.out.println();
        printBoard(board);
        boolean playing = true;
        while(playing)
        {
            try {
                Turn(board);
                System.out.println();
                printBoard(board);
                System.out.println();
            } catch (Exception e) {
                System.out.println("Try again please, input should be only number between 0 and 6.");
                printBoard(board);
            }

            if(check(board) != "continue" ) {
                if (check(board) == "R") {
                    System.out.println("The red player won.");
                    playing = false;
                }
                else if (check(board) == "Y") {
                    System.out.println("The yellow player won.");
                    playing = false;
                }
                else if (check(board) == "draw") {
                    System.out.println("Players draw!");
                    playing = false;
                }
            }
        }
    }
}


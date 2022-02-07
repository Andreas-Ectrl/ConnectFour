import java.util.Arrays;

public class BoardLogic {

    static boolean isMovePossible(int[][] currentGameBoard, int player, int col) {
        return currentGameBoard[currentGameBoard.length - 1][col] == 0;
    }


    static void makeMove(int[][] currentGameBoard, int player, int col) {
        for (int i = 0; i < currentGameBoard.length; i++){
            if (currentGameBoard[i][col] == 0) {
                currentGameBoard[i][col] = player;
                break;
            }
        }
    }

    static boolean existsWinner(int[][] currentGameBoard, int player) {

        //offDiagonalWin
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 4; y++)
                if (        currentGameBoard[x][y] != 0
                        &&  currentGameBoard[x][y] == currentGameBoard[x+1][y+1]
                        &&  currentGameBoard[x+2][y+2] == currentGameBoard[x+3][y+3]
                        &&  currentGameBoard[x+3][y+3] == currentGameBoard[x+1][y+1]){
                    return true;
                }
        }

        //mainDiagonalWin
        for (int x = 0; x < 3; x++){
            for (int y = 6; y > 2; y--)
                if (        currentGameBoard[x][y] != 0
                        &&  currentGameBoard[x][y] == currentGameBoard[x+1][y-1]
                        &&  currentGameBoard[x+2][y-2] == currentGameBoard[x+3][y-3]
                        &&  currentGameBoard[x+3][y-3] == currentGameBoard[x+1][y-1]){
                    return true;
                }
        }

        //horizontalWin
        for (int x = 0; x < currentGameBoard.length; x++){
            for (int y = 0; y < 4; y++){
                if (        currentGameBoard[x][y] != 0
                        &&  currentGameBoard[x][y] == currentGameBoard[x][y+1]
                        &&  currentGameBoard[x][y+2] == currentGameBoard[x][y+3]
                        &&  currentGameBoard[x][y+3] == currentGameBoard[x][y+1]){
                    return true;
                }
            }
        }

        //verticalWin
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < currentGameBoard[0].length; y++){

                if (        currentGameBoard[x][y]   != 0
                        &&  currentGameBoard[x][y]   == currentGameBoard[x+1][y]
                        &&  currentGameBoard[x+2][y] == currentGameBoard[x+3][y]
                        &&  currentGameBoard[x+3][y] == currentGameBoard[x+1][y]){
                    return true;
                }
            }
        }
        return false;
    }
}

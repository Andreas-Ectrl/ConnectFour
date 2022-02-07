import java.util.Arrays;

public class GameBoard {

    static int[][] genGameBoard(int row, int col) {
        return new int[row][col];
    }

    static void drawGameBoard(int[][] currentGameBoard, int oneSquareSize) {


        double xFrame = currentGameBoard.length;
        double yFrame = currentGameBoard[0].length;

        double halfSquare = (double) oneSquareSize/2;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle( (xFrame+1)*halfSquare,(yFrame-1)*halfSquare,
                (xFrame+1)*halfSquare, yFrame*halfSquare);

        double circleLocX;
        double circleLocY;

        int row = 0;
        int col = 0;

        for (circleLocX = halfSquare; circleLocX < (xFrame+1)*oneSquareSize; circleLocX += oneSquareSize){
            row = 0;
            for (circleLocY = halfSquare; circleLocY <= (yFrame-1)*oneSquareSize; circleLocY += oneSquareSize){

                if      (currentGameBoard[row][col] == 1)   StdDraw.setPenColor(StdDraw.RED);
                else if (currentGameBoard[row][col] == 2)   StdDraw.setPenColor(StdDraw.YELLOW);
                else                                        StdDraw.setPenColor(StdDraw.GRAY);

                StdDraw.filledCircle(circleLocX, circleLocY, (double) oneSquareSize/3);
                row++;
            }
            col++;
        }
    }

    //clears the board, sets all array elements to 0 in Gameboard
    static void clearBoard(int[][] currentGameBoard, int oneSquareSize){
        for (int row = 0; row < currentGameBoard.length-1; row++){
            for (int col = 0; col < currentGameBoard[0].length; col++){
                currentGameBoard[row][col] = currentGameBoard[row+1][col];
                currentGameBoard[currentGameBoard.length-1-row][col] = 0;

            }
            GameBoard.drawGameBoard(currentGameBoard, oneSquareSize);
            StdDraw.pause(500);
        }
        Arrays.fill(currentGameBoard[0], 0);
        GameBoard.drawGameBoard(currentGameBoard, oneSquareSize);
        StdDraw.pause(500);
    }

}

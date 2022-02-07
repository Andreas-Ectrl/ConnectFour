
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;


public class Game {

    // canvas settings
    private static int rowsGameBoard;
    private static int colsGameBoard;
    private static int oneSquareSize;

    private static int width;
    private static int height;
    private static int[][] myGameBoard;

    private static int player = 1;
    private static int fieldsUsed = 0;

    static void init(int rows, int cols, int squareSize){

        // game variables
        rowsGameBoard = rows;
        colsGameBoard = cols;
        oneSquareSize = squareSize;

        width = oneSquareSize * colsGameBoard;
        height = oneSquareSize * rowsGameBoard;

        myGameBoard = GameBoard.genGameBoard(rowsGameBoard, colsGameBoard);

        // set font for text
        Font font = new Font("Arial", Font.BOLD, 28);
        StdDraw.setFont(font);

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        GameBoard.drawGameBoard(myGameBoard, oneSquareSize);

        System.out.println("Player " + player + (player == 1 ? " (RED)" : " (YELLOW)") + " has to make a move!");
    }

    static void loop(){
        while (!StdDraw.isKeyPressed(KeyEvent.VK_Q)) {

            //Drawing the Helplines to mark the selected column
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            int colHelperVar = (int) StdDraw.mouseX()/(width/colsGameBoard) * oneSquareSize;
            int colHelperPos = colHelperVar + width/(2*colsGameBoard);

            StdDraw.rectangle(colHelperPos, (double) height/2 , (double) oneSquareSize/2, (double)height/2);
            StdDraw.pause(50);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.rectangle(colHelperPos, (double) height/2 , (double) oneSquareSize/2, (double)height/2);

            if (StdDraw.isMousePressed()) {

                //converting input to column for Gameboard array
                int col = (int) StdDraw.mouseX() / oneSquareSize;
                StdDraw.pause(200);

                //check if column is full
                if (BoardLogic.isMovePossible(myGameBoard, player, col)) {
                    BoardLogic.makeMove(myGameBoard, player, col);
                    fieldsUsed++;

                    //if player has won
                    if (BoardLogic.existsWinner(myGameBoard, player)) {
                        GameBoard.drawGameBoard(myGameBoard, oneSquareSize);

                        StdDraw.setPenColor(StdDraw.GREEN);
                        StdDraw.text((double) width/2, (double) height/2, "Player " + player +" has won!!!");
                        StdDraw.pause(3000);

                        fieldsUsed = 0;
                        GameBoard.clearBoard(myGameBoard, oneSquareSize);


                        if (player == 1) player = 2;
                        else if (player == 2) player = 1;

                        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                        StdDraw.text((double) width/2, (double) height/2, "Next Round!");
                        StdDraw.pause(1000);
                        System.out.println("Player " + player + (player == 1 ? " (RED)" : " (YELLOW)") + " has to make a move!");
                        GameBoard.drawGameBoard(myGameBoard, oneSquareSize);
                    }
                    // else if no winner yet
                    else {
                        GameBoard.drawGameBoard(myGameBoard, oneSquareSize);
                        if (fieldsUsed == rowsGameBoard*colsGameBoard){


                            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                            StdDraw.text((double) width/2, (double) height/2, "Board full!");
                            StdDraw.pause(1000);

                            GameBoard.clearBoard(myGameBoard, oneSquareSize);
                            fieldsUsed = 0;

                            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                            StdDraw.text((double) width/2, (double) height/2, "Try again!");
                            StdDraw.pause(1000);
                            GameBoard.drawGameBoard(myGameBoard, oneSquareSize);

                        }
                        //switch player
                        if (player == 1) player = 2;
                        else player = 1;
                        System.out.println("Player " + player + (player == 1 ? " (RED)" : " (YELLOW)") + " has to make a move!");
                    }
                }
                //if column is full
                else {
                    StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                    StdDraw.text((double) width/2, (double) height/2, "Column already full!");
                }

            }
        }

    }

}

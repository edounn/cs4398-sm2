
import java.util.*;
import java.swing.*;
import java.awt.*;
import java.io.*;
import java.imageio.ImageIO;
import Shape;

/**
 *
 */
public class Board{

  private final int BOARD_WIDTH = 10;
  private final int BOARD_HEIGHT = 20;
  private final int BLOCK_SIZE = 30;

  private int[][] board;
  private Timer timer; //Timer for game
  private BufferedImage blocks;
  private Shape[] shapes;
  private Shape currentPiece;
  private boolean gameOver;
  private boolean isPaused;



  /**
   * Default constructor
   */
  public Board() {
    board = int[BOARD_HEIGHT][BOARD_WIDTH];
    shapes = Shape[7];
    gameOver = false;
    isPaused = false;

    try {
      blocks = ImageIO.read(Board.class.getResource("/tiles.png"));
    }catch (IOException e) {
      e.printStackTrace();
    }

    shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{
  	   {1, 1, 1, 1} // LBlock
    }, this);

    shapes[1] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{
      {1, 1, 0}, // ZBlock
  	  {0, 1, 1}
    }, this);

    shapes[2] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{
  	   {0, 1, 1}, // SBlock
	     {1, 1, 0}
    }, this);
  }


  /**
  * Public function which starts the game.
  * Takes no parameters, returns nothing.
  */
  public void Start(){

  }

  /**
  * Private function which clears the board by setting array index to null.
  * Takes no parameters, returns nothing.
  */
  private void clearBoard(){
  }

  /**
  * Private function which creates a new Tetrimo.
  * Takes no parameters, returns nothing.
  */
  private void newPiece(){
  }

}

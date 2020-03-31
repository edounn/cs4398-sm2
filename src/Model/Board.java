package Model;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 */
public class Board  extends JPanel{

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
  private final int FPS = 60;
  private final int DELAY = 1000/FPS;



  /**
   * Default constructor
   */
  public Board(){
    board = new int[BOARD_HEIGHT][BOARD_WIDTH];
    shapes = new Shape[7];
    timer = new Timer(DELAY, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});

    try {
      blocks = ImageIO.read(Board.class.getResource("/images/tiles.jpg"));
    }catch (IOException e) {
      e.printStackTrace();
    }

    shapes[0] = new Shape(blocks.getSubimage(0, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
  	   {1, 1, 1, 1} // LBlock
    }, this);

    shapes[1] = new Shape(blocks.getSubimage(0, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
      {1, 1, 0}, // ZBlock
  	  {0, 1, 1}
    }, this);

    shapes[2] = new Shape(blocks.getSubimage(0, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
  	   {0, 1, 1}, // SBlock
	     {1, 1, 0}
    }, this);
  }


  /**
  * Public function which starts the game.
  * Takes no parameters, returns nothing.
  */
  public void Start(){
    gameOver = false;
    isPaused = false;
  }

  public boolean getGameOver(){
    return gameOver;
  }
  public boolean getIsPaused(){
    return isPaused;
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

  public void update(){
		currentPiece.update();
		if(gameOver)
			timer.stop();
	}
}

package Model;

import com.sun.crypto.provider.BlowfishCipher;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 */
public class Board  extends JPanel implements KeyListener {

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
  private final int DELAY = 1000 / FPS;


  /**
   * Default constructor
   */
  public Board(){
    try {
      blocks = ImageIO.read(new FileImageInputStream(new File("src/images/tiles.jpg")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    board = new int[BOARD_HEIGHT][BOARD_WIDTH];
    for (int i = 0; i < BOARD_HEIGHT; i++) {
      for (int j = 0; j < BOARD_WIDTH; j++) {
        board[i][j] = 0;
      }
    }
    shapes = new Shape[7];

    shapes[0] = new Shape(blocks.getSubimage(0, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
            {1, 1, 1, 1} // IShape
    }, this, 1);

    shapes[1] = new Shape(blocks.getSubimage(BLOCK_SIZE, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
            {1, 1, 0},
            {0, 1, 1}   // ZShape
    }, this, 2);

    shapes[2] = new Shape(blocks.getSubimage(BLOCK_SIZE*2, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
            {0, 1, 1},
            {1, 1, 0}   // S-Shape
    }, this, 3);
    shapes[3] = new Shape(blocks.getSubimage(BLOCK_SIZE*3, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
            {1, 1, 1},
            {0, 0, 1}   // J-Shape
    }, this, 4);

    shapes[4] = new Shape(blocks.getSubimage(BLOCK_SIZE*4, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
            {1, 1, 1},
            {1, 0, 0}   // L-Shape
    }, this, 5);

    shapes[5] = new Shape(blocks.getSubimage(BLOCK_SIZE*5, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
            {1, 1, 1},
            {0, 1, 0}   // T-Shape
    }, this, 6);

    shapes[6] = new Shape(blocks.getSubimage(BLOCK_SIZE*6, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
            {1, 1},
            {1, 1}   // O-Shape
    }, this, 7);

    timer = new Timer(DELAY, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        update();
        repaint();
      }
    });

   newPiece();
  }


  /**
   * Public function which starts the game.
   * Takes no parameters, returns nothing.
   */
  public void Start() {
    gameOver = false;
    isPaused = false;

    timer.start();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    currentPiece.render(g);

    for(int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] != 0) {
          g.drawImage(blocks.getSubimage((board[row][col] - 1) * BLOCK_SIZE, 0, BLOCK_SIZE, BLOCK_SIZE), col * BLOCK_SIZE, row * BLOCK_SIZE, null);
        }
      }
    }
    for(int i = 0; i < BOARD_HEIGHT; i++){
      g.drawLine(0, i*BLOCK_SIZE, BOARD_WIDTH*BLOCK_SIZE, i*BLOCK_SIZE);
    }
    for(int j = 0; j < BOARD_WIDTH; j++){
      g.drawLine(j*BLOCK_SIZE, 0, j*BLOCK_SIZE, BOARD_HEIGHT*BLOCK_SIZE);
    }
  }

  public boolean getGameOver() {
    return gameOver;
  }

  public boolean getIsPaused() {
    return isPaused;
  }

  public int[][] getBoard() {
    return board;
  }

  public int getBlockSize(){
    return BLOCK_SIZE;
  }



  /**
   * Private function which creates a new Tetrimo.
   * Takes no parameters, returns nothing.
   */
  public void newPiece() {
     currentPiece =  shapes[(int)Math.random()*shapes.length];

  }

  @Override
  public void keyTyped(KeyEvent e) {
    //Stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
      currentPiece.setDeltaX(-1);
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
      currentPiece.setDeltaX(1);
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
      currentPiece.speedDown();
    if (e.getKeyCode() == KeyEvent.VK_UP)
      currentPiece.rotate();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
      currentPiece.normalSpeed();
  }

  public void update(){
		currentPiece.update();
		if(gameOver)
			timer.stop();
	}
}

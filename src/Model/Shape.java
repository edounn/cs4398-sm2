package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
* Public class which represents the Tetrimos
*/
public class Shape {

  private BufferedImage block;
  private int[][] coords;
  private Board board;
  private int deltaX;
  private int x, y;
  private boolean collisionDetected = false;
  private int normalSpeed = 600;
  private int speedDown = 60;
  private int speedUp = 1200;
  private int currentSpeed;
  private int color;
  private boolean moveX = false;
  private long time;
  private long lastTime;
  private int[][] reference;

  /**
   * Default constructor for the Shape/ Tetrimo class
   * @param block A BufferedImage of a jpeg, which is the color to use for the Tetrimo
   * @param coords A 2d array which represents the blocks space
   * @param board The game Board
   * @param color An int representing the color of the Tetrimo
   */
   public Shape(BufferedImage block, int[][] coords, Board board, int color)
   {
	    this.block = block;
	    this.coords = coords;
	    this.board = board;
	    this.color = color;
	    currentSpeed = normalSpeed;
	    x = 4;
	    y = 0;
     time = 0;
     lastTime = System.currentTimeMillis();
     reference = new int[coords.length][coords[0].length];

     System.arraycopy(coords, 0, reference, 0, coords.length);
   }

  /**
   * A public void method which updates the current piece's game state
   * Detects collisions and game overs.
   */
  public void update() {

    time += System.currentTimeMillis() - lastTime;
    lastTime = System.currentTimeMillis();

    if(collisionDetected){
      for(int row = 0; row < coords.length; row++){
        for(int col = 0; col < coords[row].length; col++){
          if(coords[row][col] != 0){
            board.getBoard()[x+row][y+col] = color;
          }
        }
      }
      checkLine();
      board.setCurrentPiece();
    }
    if(!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)) {
      for(int row = 0; row < coords.length; row++) {
        for (int col = 0; col < coords[row].length; col++) {
          if (coords[row][col] != 0) {
            if (board.getBoard()[y + row][x + deltaX + col] != 0) {
              moveX = false;
            }
          }
        }
      }
      if(moveX) {
        x += deltaX;
      }
    }
    if(!(y + 1 + coords.length > 20)) {

      for(int row = 0; row < coords.length; row++) {
        for (int col = 0; col < coords[row].length; col++) {
          if (coords[row][col] != 0) {
            if (board.getBoard()[y + row + 1][col + x] != 0) {
              collisionDetected = true;
            }
          }
        }
      }
      if(time > currentSpeed) {
        y++;
        time = 0;
      }
    } else {
      collisionDetected = true;
    }
    deltaX = 0;
    moveX = true;
  }

  /**
   * A public void method responsible for drawing the tetrimo on the board
   * @param g the current game graphic
   */
  public void render(Graphics g) {
    for(int row = 0; row < coords.length; row++) {
      for (int col = 0; col < coords[row].length; col++) {
        if (coords[row][col] != 0) {
          g.drawImage(block, col * board.getBlockSize() + x * board.getBlockSize(),row * board.getBlockSize() + y * board.getBlockSize(), null);
        }
      }
    }
  }

  /**
   * A public void method to check if a line is to be cleared
   */
  public void checkLine() {
    int height = board.getBoard().length - 1;
    for(int i = height; i > 0; i--){
      int count = 0;
      for(int j = 0; j < board.getBoard()[0].length; j++){
        if(board.getBoard()[i][j] != 0)
          count ++;
        board.getBoard()[height][j] = board.getBoard()[i][j];
      }
      if(count < board.getBoard()[0].length) {
        height--;
      }
    }
  }

  /**
   * A public void method which handles rotation of the tetrimos
   */
  public void rotate() {
    if(collisionDetected) {
      return;
    }
    int[][] rotatedMatrix;
    rotatedMatrix = getTranspose(coords);
    rotatedMatrix = getReverseMatrix(rotatedMatrix);
    if(x + rotatedMatrix[0].length > 10 || y + rotatedMatrix.length > 20) {
      return;
    }
    for(int row = 0; row < rotatedMatrix.length; row++) {
      for(int col = 0; col < rotatedMatrix[0].length; col++){
        if(board.getBoard()[y + row][x + col] != 0){
          return;
        }
      }
    }
    coords = rotatedMatrix;
  }

  /**
   * A public method which returns the transposed matrix representing a tetrimo
   * @param coords A 2d array which represents the coordinate matrix of a tetrimo
   * @return returns the newly transposed coordinate matrix of a tetrimo
   */
  public int[][] getTranspose(int[][] coords) {
    int[][] newMatrix = new int[coords[0].length][coords.length];
    for(int i = 0; i < coords.length; i++) {
      for (int j = 0; j < coords[0].length; j++) {
        newMatrix[j][i] = coords[i][j];
      }
    }
    return newMatrix;
  }

  /**
   * A public method that returns a 2d int array representing the reversed tetrimo
   * @param rotatedMatrix a 2d int array which represents the coordinate matrix of a tetrimo
   * @return a 2d int array representing the reversed coordinate matrix of a tetrimo
   */
  public int[][] getReverseMatrix(int[][] rotatedMatrix) {
    int middle = rotatedMatrix.length / 2;

    for(int i = 0; i < middle; i++){
      int[] m = rotatedMatrix[i];
      rotatedMatrix[i] = rotatedMatrix[rotatedMatrix.length - i - 1];
      rotatedMatrix[rotatedMatrix.length - i - 1] = m;
    }
    return rotatedMatrix;
  }

  /**
   * A public void method which sets deltaX
   * @param deltaX an int representing the change in the x coordinate of a tetrimo
   */
  public void setDeltaX(int deltaX) {
      this.deltaX = deltaX;
  }

  /**
   * A public void method which sets the current tetrimo speed to the normal speed
   */
  public void normalSpeed() {
      currentSpeed = normalSpeed;
  }

  /**
   * a public void method which sets the current speed to the slower option
   */
  public void speedDown() {
      currentSpeed = speedDown;
  }

  /**
   * a public void method which sets the current speed the the faster option
   */
  public void speedUp() {
    currentSpeed = speedUp;
  }

  /**
   * A public method which returns BufferedImage that represents the current tetrimo
   * @return a BufferedImage that represents the current tetrimo
   */
  public BufferedImage getBlock() {
      return block;
  }

  /**
   * A public method which returns an int representing the color of the current tetrimo
   * @return an int representing the color of the current tetrimo
   */
  public int getColor() {
      return color;
  }

  /**
   * A public method which returns a 2d int array representing the coordinate matrix of the current tetrimo
   * @return
   */
  public int[][] getCoords() {
    return coords;
  }

}

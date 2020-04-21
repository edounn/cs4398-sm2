package Model;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

/**
*
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
  private int currentSpeed;
  private int color;
  private boolean moveX = false;
  private long time;
  private long lastTime;

  /**
   * Default constructor
   */
   public Shape(BufferedImage block, int[][] coords, Board board, int color)
   {
	    this.block = block;
	    this.coords = coords;
	    this.board = board;
	    this.color = color;
	    currentSpeed = normalSpeed;
	    x = 3;
	    y = 0;
     time = 0;
     lastTime = System.currentTimeMillis();
   }

  /**
   *
   */
  public void update() {
    if(collisionDetected){
      for(int row = 0; row < coords.length; row++){
        for(int col = 0; col < coords[row].length; col++){
          if(coords[row][col] != 0){
            board.getBoard()[x+row][y+col] = color;
          }
        }
      }
      checkLine();
      board.newPiece();
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
   *
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
   *
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
   *
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

  public int[][] getTranspose(int[][] coords) {
    int[][] newMatrix = new int[coords[0].length][coords.length];
    for(int i = 0; i < coords.length; i++) {
      for (int j = 0; j < coords[0].length; j++) {
        newMatrix[j][i] = coords[i][j];
      }
    }
    return newMatrix;
  }

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
   *
   */
  public void setDeltaX(int deltaX) {
      this.deltaX = deltaX;
  }

  /**
   *
   */
  public void normalSpeed() {
      currentSpeed = normalSpeed;
  }

  /**
   *
   */
  public void speedDown() {
      currentSpeed = speedDown;
  }

  /**
   *
   */
  public BufferedImage getBlock() {
      return block;
  }

  /**
   *
   */
  public int getColor() {
      return color;
  }

}

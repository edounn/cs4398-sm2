import java.awt.image.BufferedImage;
import java.awt.Graphics;

/**
*
*/
public class Shape {

  private BufferedImage block;
  private int[][] coords;
  private Board board;

  /**
   * Default constructor
   */
   public Shape(BufferedImage block, int[][] coords, Board board)
   {
	    this.block = block;
	    this.coords = coords;
	    this.board = board;
   }

   public void render(Graphics g){}
  /**
   *
   */
  public int x;

  /**
   *
   */
  public int y;

  /**
   *
   */
  public int color;

  /**
   *
   */
  public int collisions;


  /**
   *
   */
  public void update() {
      // TODO implement here
  }

  /**
   *
   */
  public void render() {
      // TODO implement here
  }

  /**
   *
   */
  public void checkLine() {
      // TODO implement here
  }

  /**
   *
   */
  public void rotate() {
      // TODO implement here
  }

  /**
   *
   */
  public void getTranspose() {
      // TODO implement here
  }

  /**
   *
   */
  public void getReverseMatrix() {
      // TODO implement here
  }

  /**
   *
   */
  public void setDeltaX() {
      // TODO implement here
  }

  /**
   *
   */
  public void normalSpeed() {
      // TODO implement here
  }

  /**
   *
   */
  public void speedDown() {
      // TODO implement here
  }

  /**
   *
   */
  public void getBlock() {
      // TODO implement here
  }

  /**
   *
   */
  public void getColor() {
      // TODO implement here
  }

}

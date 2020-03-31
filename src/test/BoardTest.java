import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class BoardTest{

  private Board board = new Board();
  
  @Test
  public void testStart(){
    board.start();
    assertEquals("Testing game state: ", false, board.getGameOver());
    assertEquals("Testing paused state: ", false, board.getIsPaused());
  }



}

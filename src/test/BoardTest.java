package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import Model.Board;

public class BoardTest{

  private Board board = new Board();
  
  @Test
  public void testStart(){
    board.Start();
    assertEquals("Testing game state: ", false, board.getGameOver());
    assertEquals("Testing paused state: ", false, board.getIsPaused());
  }



}

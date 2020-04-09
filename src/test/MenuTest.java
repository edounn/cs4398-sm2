package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import Model.Shape;
import Model.Board;
import View.Menu;

import javax.imageio.ImageIO;


public class MenuTest {
    private Board game;
    private Menu menu;

    @Test
    public void testStartGame(){
        menu = new Menu(new String[1]);
        assertEquals("Testing before startGame(): ", false, menu.started);
        menu.startGame();
        assertEquals("Testing after startGame(): ", true, menu.started);
    }
}

package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import Model.Shape;
import Model.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * A JUnit Test Class which Tests the Shape Class
 */
public class ShapeTest {
    private Shape shape;
    private BufferedImage blocks;
    private Board board;

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private final int BLOCK_SIZE = 30;

    /**
     * Initializes the objects needed for texting
     */
    @Before
    public void init(){
        try {
            board = new Board();
            blocks = ImageIO.read(Board.class.getResource("/images/tiles.jpg"));
        }catch(Exception e){System.out.println("Error");}
    }

    /**
     * A test for the Shape constructor
     */
    @Test
    public void testShape(){
        shape = new Shape(blocks.getSubimage(0, 0, BLOCK_SIZE, BLOCK_SIZE), new int[][]{
                {1, 1, 1, 1} // LBlock
        }, board, 1);

        assertEquals("Testing block shape: ", shape, shape.getBlock());
    }
}

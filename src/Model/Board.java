
import java.util.*;

/**
 * 
 */
public class Board {

    /**
     * Default constructor
     */
    public Board() {
		try {
			blocks = ImageIO.read(Board.class.getResource("/tiles.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// shapes
		
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
     * 
     */
    public void BufferedImage blocks;

    /**
     * 
     */
    public void Shapes;

    /**
     * 
     */
    public void Timer timer;


    /**
     * 
     */
    public void Board() {
        // TODO implement here
    }

}
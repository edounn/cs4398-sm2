package Model;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A
 */
public class Board  extends JPanel implements KeyListener {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private final int BLOCK_SIZE = 30;

    private int[][] board;
    private Timer timer;
    private Timer loop;
    public Clip bgm;
    private BufferedImage blocks;
    private Shape[] shapes;
    private static Shape currentPiece, nextPiece;
    private boolean gameOver = false;
    private boolean isPaused = false;
    private final int FPS = 60;
    private final int DELAY = 1000 / FPS;
    private int score = 0;

    /**
     * Constructor
     * Creates array of shapes and starts background music and timer
     */
    public Board(){

        try {
            blocks = ImageIO.read(new FileImageInputStream(new File("src/images/tiles.jpg")));
        } catch (IOException e) {e.printStackTrace();}

        // Generates the song, and loops it.
        try {
            bgm = AudioSystem.getClip();
            bgm.open(AudioSystem.getAudioInputStream(new File("src/images/bgm.wav")));
        }catch (Exception e) {e.printStackTrace();}

        bgm.start();
        bgm.loop(Clip.LOOP_CONTINUOUSLY);

        // Generates the loop check to loop game state.
        loop = new Timer(DELAY, new gameLoop());

        // Generates shapes and board from assets.
        board = new int[BOARD_HEIGHT][BOARD_WIDTH];
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
    }

    /**
     * Overidden from JPanel paints board for game play
     * @param g Is a Graphic representing the current board layout.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Draws BG.
        g.drawImage(new ImageIcon("src/images/background.jpg").getImage(), 0, 0, 720, 640, this);

        // Draws board.
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

        // Draws next piece.
        for(int row = 0; row < nextPiece.getCoords().length; row ++) {
            for(int col = 0; col < nextPiece.getCoords()[0].length; col ++) {
                if(nextPiece.getCoords()[row][col] != 0) { g.drawImage(nextPiece.getBlock(), col*30 + 320, row*30 + 50, null); }
            }
        }
        currentPiece.render(g);

        if(gameOver){
            removeAll();
            updateUI();

            add(Box.createVerticalStrut(100));

            JLabel jlabel = new JLabel("GAME OVER!");
            jlabel.setFont(new Font("Graphite Std", Font.BOLD, 125));
            jlabel.setForeground(Color.RED);
            jlabel.setVerticalAlignment(jlabel.TOP);
            jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            add(jlabel);
        }

        g.drawString("Score:", Window.WIDTH - 125, Window.HEIGHT/2);
        g.drawString(score+"", Window.WIDTH - 125, Window.HEIGHT/2 + 30);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(0, 0, 0, 100));

        for(int i = 0; i <= BOARD_HEIGHT; i++)
        {
            g2d.drawLine(0, i*BLOCK_SIZE, BOARD_WIDTH*BLOCK_SIZE, i*BLOCK_SIZE);
        }
        for(int j = 0; j <= BOARD_WIDTH; j++)
        {
            g2d.drawLine(j*BLOCK_SIZE, 0, j*BLOCK_SIZE, BOARD_HEIGHT*30);
        }
    }

    /**
     * A public function which sets the current Tetrimo
     */
    public void setCurrentPiece(){
        currentPiece = nextPiece;
        setNextPiece();

        for(int row = 0; row < currentPiece.getCoords().length; row++) {
            for (int col = 0; col < currentPiece.getCoords()[row].length; col++) {
                if (currentPiece.getCoords()[row][col] != 0) {

                    if (board[row][col + 3] != 0) {
                        gameOver = true;
                    }
                }
            }
        }
    }

    /**
     * A public void function that gets the next piece
     */
    public void setNextPiece(){
        int index = (int)(Math.random()*shapes.length);
        Shape newShape = new Shape(shapes[index].getBlock(), shapes[index].getCoords(),
                this, shapes[index].getColor());

    }

    /**
     * A sub class which implements ActionListener to run the game.
     */
    class gameLoop implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            update();
            repaint();
        }
    }

    /**
     * A public void function responsible for starting the game
     */
    public void startGame(){
        stopGame();
        setNextPiece();
        gameOver = false;
        loop.start();
    }

    /**
     * A public void method which stops the game state and clears the board
     */
    public void stopGame(){
        score = 0;

        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col ++)
            {
                board[row][col] = 0;
            }
        }
        loop.stop();
    }

    /**
     * A public void method which handles the tick by tick movement of the game.
     */
    public void update(){
		currentPiece.update();

		if(gameOver)
			timer.stop();
    }

    /**
     * A public getter for gameOver state
     * @return a boolean that represents current game state
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * A public getter for the paused game state
     * @return a boolean that represents the current game state
     */
    public boolean getIsPaused() {
        return isPaused;
    }

    /**
     * A public getter for the current board state
     * @return a 2d array that represents the current layout of the game board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * A public getter for the block size
     * @return an int constant which represents the size of a game piece.
     */
    public int getBlockSize(){
        return BLOCK_SIZE;
    }

    private Timer btn = new Timer(300, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            btn.stop();
        }});

    /**
     * A public void function overridden from the KeyListener
     * @param e a KeyEvent representing the current pressed key
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP)
            currentPiece.rotate();
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            currentPiece.setDeltaX(1);
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            currentPiece.setDeltaX(-1);
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            currentPiece.speedUp();
    }

    /**
     * An overridden stub from KeyListener
     * @param e a KeyEven representing the current pressed key
     */
    @Override
    public void keyTyped (KeyEvent e) {}
    /**
     * An overridden stub from KeyListener
     * @param e a KeyEven representing the current pressed key
     */
    @Override
    public void keyReleased (KeyEvent e) {}
}

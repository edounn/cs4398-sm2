package View;

import Model.Board;

import javax.swing.*;

/**
 * A public class which handles the game's UI
 */
public class Viewer{

    private JFrame viewer;
    private Board game;
    private Menu menu;

    /**
     * Default constructor for the games UI
     */
    public Viewer() {

        viewer = new JFrame("Tetris Game");
        viewer.setSize(720, 640);
        viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewer.setResizable(false);

        game = new Board();
        menu = new Menu(this);
        viewer.addKeyListener(game);
        viewer.addMouseListener(menu);
        viewer.add(menu);
        viewer.setVisible(true);
    }

    /**
     * A public void method which handles starting the game state
     */
    public void beginGame() {
        viewer.remove(menu);
        viewer.add(game);
        game.startGame();
        viewer.revalidate();
    }

    /**
     * A main to run the game.
     * @param args
     */
    public static void main(String[] args) {
        new Viewer();
    }

}





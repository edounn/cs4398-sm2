package View;

import javax.swing.JFrame;

import Model.Board;

public class gameFrame extends JFrame {
    Menu menu;
    Board board;
    public gameFrame(String[] arg) {

        setTitle("Tetris");
        setSize(720, 640);

        setResizable(false);

        String[] path = arg;
        menu = new Menu(path, this);
        board = new Board();
        add(menu);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void start(){
        this.remove(menu);
        this.add(board);
        board.Start();
        board.setVisible(true);
        this.repaint();
    }

    public static void main(String[] args) {
        String[] path = args;
        new gameFrame(path);
    }
}

package View;

import javax.swing.JFrame;

public class Main extends JFrame {
    public Main(String[] arg) {

        setTitle("Tetris");
        setSize(720, 640);

        setResizable(false);

        String[] path = arg;
        Menu menu = new Menu(path);
        add(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        String[] path = args;
        new Main(path);
    }
}

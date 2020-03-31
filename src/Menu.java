import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.nio.Buffer;
import javax.swing.*;
import Model.Board;

public class Menu extends JPanel {
    public boolean started = false;
    public Board game = new Board();

    public String[] args;



    public Menu(String[] arg) {
        args = arg;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(30));

        JLabel jlabel = new JLabel("TETRIS!");

        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));

        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);



        add(jlabel);

        add(Box.createVerticalStrut(100));

        Button playbutton = new Button("Play");
        playbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        playbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create instance to play tetris
                  startGame();
            }
        });
        add(playbutton);
}

    public void startGame(){
        System.out.println("start!");
        game.Start();
    }
    @Override
    public void paintComponent (Graphics g) {

        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon("src/Tetris.jpg").getImage(), 0, 0, 720, 640, this);
        } else {
            setBackground(Color.RED);
        }
    }
}

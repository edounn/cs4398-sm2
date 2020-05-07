package View;


import Model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;


public class Menu extends JPanel implements MouseListener{
    public Menu(Viewer viewer) {

        JLabel jlabel = new JLabel("TETRIS");
        jlabel.setFont(new Font("Graphite Std", Font.BOLD, 50));
        jlabel.setForeground(Color.WHITE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(jlabel);

        add(Box.createVerticalStrut(20));

        Button playbutton = new Button("Play");
        playbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        playbutton.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        playbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.beginGame();
            }
        });
        add(playbutton);

        add(Box.createVerticalStrut(20));

        Button themeButton = new Button("Themes");
        themeButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        themeButton.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        themeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(viewer);
            }
        });
        add(themeButton);

        add(Box.createVerticalStrut(20));

        Button settingsButton = new Button("Settings");
        settingsButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        settingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(settingsButton);
    }

    public void changeTheme(Viewer viewer) {
        removeAll();
        updateUI();

        add(Box.createVerticalStrut(30));

        JLabel jlabel = new JLabel("Change Settings");
        jlabel.setFont(new Font("Graphite Std", Font.BOLD, 50));
        jlabel.setForeground(Color.WHITE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(jlabel);

        add(Box.createVerticalStrut(20));

        Button theme1Button = new Button("Theme 1");
        theme1Button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        theme1Button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  Board.setTheme(0);
                viewer.beginGame();
            }
        });
        add(theme1Button);

        add(Box.createVerticalStrut(20));

        Button theme2Button = new Button("Theme 2");
        theme2Button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        theme2Button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // Board.setTheme(1);
                viewer.beginGame();
            }
        });
        add(theme2Button);

        add(Box.createVerticalStrut(20));

        Button theme3Button = new Button("Theme 3");
        theme3Button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        theme3Button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // Board.setTheme(2);
                viewer.beginGame();
            }
        });
        add(theme3Button);


    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(new ImageIcon("src/images/background.jpg").getImage(), 0, 0, 720, 640, this);

    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
}


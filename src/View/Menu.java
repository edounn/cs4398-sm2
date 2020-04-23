package View;

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
import sun.applet.Main;

public class Menu extends JPanel {
    public boolean started = false;
    public String filename = "./Tetris.jpg";
    public Board game;
    public String[] args;


    public Menu(String[] arg) {
        args = arg;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mainMenu();
    }

    public void mainMenu() {
        removeAll();
        updateUI();
      
        add(Box.createVerticalStrut(30));

        JLabel jlabel = new JLabel("TETRIS!");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(jlabel);

        add(Box.createVerticalStrut(20));

        Button playbutton = new Button("Play");
        playbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        playbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(playbutton);

        add(Box.createVerticalStrut(20));

        Button themeButton = new Button("Themes");
        themeButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        themeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme();
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

    public void changeTheme() {
        removeAll();
        updateUI();

        add(Box.createVerticalStrut(30));

        JLabel jlabel = new JLabel("Change Settings");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(jlabel);

        add(Box.createVerticalStrut(20));

        Button theme1Button = new Button("Theme 1");
        theme1Button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        theme1Button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filename = "./Tetris.jpg";
            }
        });
        add(theme1Button);

        add(Box.createVerticalStrut(20));

        Button theme2Button = new Button("Theme 2");
        theme2Button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        theme2Button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filename = "./Tetris1.jpg";
            }
        });
        add(theme2Button);

        add(Box.createVerticalStrut(20));

        Button theme3Button = new Button("Theme 3");
        theme3Button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        theme3Button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filename = "./Tetris2.jpg";
            }
        });
        add(theme3Button);

        add(Box.createVerticalStrut(30));

        Button okButton = new Button("OK");
        okButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        okButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });
        add(okButton);

    }

    @Override
    public void paintComponent (Graphics g) {

        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon(filename).getImage(), 0, 0, 720, 640, this);

        } else {
            setBackground(Color.RED);
        }
    }

}


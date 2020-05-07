package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JFrame;
import Model.Board;


public class Viewer{

    private JFrame viewer;
    private Board game;
    private Menu menu;

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

    public void beginGame() {
        viewer.remove(menu);
        viewer.add(game);
        game.startGame();
        viewer.revalidate();
    }


    public static void main(String[] args) {
        new Viewer();
    }

}





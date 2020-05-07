package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A public class which extends JPanel and implements MouseListener
 */
public class Menu extends JPanel implements MouseListener{
    /**
     * A constructor which sets up the default panel
     * @param viewer A Viewer which controls the game's UI
     */
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

    /**
     * A public void function which changes the games current theme
     * @param viewer A Viewer which controls the games overall UI
     */
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

    /**
     * An overridden public method which paints the menu onto the game UI
     * @param g a Graphic which is the game's UI
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(new ImageIcon("src/images/background.jpg").getImage(), 0, 0, 720, 640, this);

    }

    /**
     * An overridden public void stub
     * @param e A MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * An overridden public void stub
     * @param e A MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }
    /**
     * An overridden public void stub
     * @param e A MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    /**
     * An overridden public void stub
     * @param e A MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e){}
    /**
     * An overridden public void stub
     * @param e A MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e){}
}


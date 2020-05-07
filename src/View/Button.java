package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A Button class which extends JButton and implements MouseListener
 */
public class Button extends JButton implements MouseListener {

    Dimension size = new Dimension(150, 75);

    boolean hover = false;
    boolean click = false;

    String text = "";

    /**
     * A Constructor for the custom Button Class
     * @param text A string which is the button text
     */
    public Button(String text) {
        setVisible(true);
        setFocusable(true);
        setContentAreaFilled(false);
        setBorderPainted(false);

        this.text = text;

        addMouseListener(this);
    }

    /**
     * An overridden method which paints the button on the screen
     * @param g A Graphic which is the current screen
     */
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (click) {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,150,75);
        }

        g.setColor(new Color(100,0, hover ? 255 : 180));

        g.fillRect(0,0,150,7);
        g.fillRect(0,68,150,7);
        g.fillRect(0,0,7,75);
        g.fillRect(143,0,7,75);

        g.setColor(new Color (0, 10, 80));

        g.fillRect(14, 14, 122,47);

        g.setColor(Color.WHITE);

        g.setFont(Font.decode("ariel-BOLD-12"));

        FontMetrics metrics = g.getFontMetrics();

        int width = metrics.stringWidth(text);

        g.drawString(text, 75 - width / 2, 37);

    }


    /**
     * A public overridden method which returns the Buttons preferred size
     * @return A Dimension which represents the preferred size of the Button
     */
    @Override

        public Dimension getPreferredSize() {

            return size;

        }


    /**
     * A public overridden function which returns the max size of the Button
     * @return A Dimension which represents the max size of the button
     */
    @Override

        public Dimension getMaximumSize() {

            return size;

        }


        /**
         * A public overridden function which returns the min size of the Button
         * @return A Dimension which represents the min size of the button
         */
        @Override

        public Dimension getMinimumSize() {

            return size;

        }

    /**
     * A public void function which sets the button text
     * @param text A string that is the desired button text
     */
    public void setButtonText (String text) {

            this.text = text;

        }

    /**
     * A public getter which returns the Button's text
     * @return A string containing the Button's text
     */
    public String getButtonText() {

            return text;

        }


        /**
         * An overridden public function which notifies the button that the mouse is hovering
         * @param e is a MouseEvent
         */
        @Override
        public void mouseEntered(MouseEvent e) {

            hover = true;

        }

    /**
     * An overridden public function which notifies the button that the mouse is no longer hovering
     * @param e is a MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;
    }


    /**
     * An overridden public function which notifies the button that the mouse is pressed down
     * @param e is a MouseEvent
     */
    @Override

    public void mousePressed(MouseEvent e) {

        click = true;

    }


    /**
     * An overridden public function which notifies the button that the mouse has been released
     * @param e is a MouseEvent
     */
    @Override

    public void mouseReleased(MouseEvent e) {

        click = false;

    }


    /**
     * An overridden public function which notifies the button that the mouse has been clicked
     * @param e is a MouseEvent
     */
    @Override

    public void mouseClicked(MouseEvent e) {

        click = false;

    }


    }

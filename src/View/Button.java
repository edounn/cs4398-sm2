import java.awt.*;

import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Button extends JButton implements MouseListener {

    Dimension size = new Dimension(150, 75);

    boolean hover = false;
    boolean click = false;

    String text = "";

    public Button(String text) {
        setVisible(true);
        setFocusable(true);
        setContentAreaFilled(false);
        setBorderPainted(false);

        this.text = text;

        addMouseListener(this);
    }



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

        g.setColor(new Color (100, 10, 30));

        g.fillRect(14, 14, 122,47);

        g.setColor(Color.WHITE);

        g.setFont(Font.decode("ariel-BOLD-12"));

        FontMetrics metrics = g.getFontMetrics();

        int width = metrics.stringWidth(text);

        g.drawString(text, 75 - width / 2, 37);

    }



        @Override

        public Dimension getPreferredSize() {

            return size;

        }



        @Override

        public Dimension getMaximumSize() {

            return size;

        }



        @Override

        public Dimension getMinimumSize() {

            return size;

        }

        public void setButtonText (String text) {

            this.text = text;

        }

        public String getButtonText() {

            return text;

        }

        @Override

        public void mouseEntered(MouseEvent e) {

            hover = true;

        }

        @Override

        public void mouseExited(MouseEvent e) {

            hover = false;

        }



        @Override

        public void mousePressed(MouseEvent e) {

            click = true;

        }



        @Override

        public void mouseReleased(MouseEvent e) {

            click = false;

        }



        @Override

        public void mouseClicked(MouseEvent e) {

            click = false;

        }


    }

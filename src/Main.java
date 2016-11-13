import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Пользователь on 28.09.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception{

        /** //Tetris code
         *
         */
        /**
        Window window = new Window("tetris");
        window.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        window.core.setRotation(-1);
                        break;
                    case KeyEvent.VK_DOWN:
                        window.core.setRotation(+1);
                        break;
                    case KeyEvent.VK_LEFT:
                        window.core.setShift(-1);
                        //System.out.println("key pressed" + window.core.getPosition());
                        break;
                    case KeyEvent.VK_RIGHT:
                        window.core.setShift(+1);
                        break;
                    case KeyEvent.VK_SPACE:
                        window.core.errTest();
                        window.core.eraseLine();
                        window.core.refreshCoord();
                        window.core.dropFigure();
                        window.core.wellinit();
                        window.core.drawFigure();
                        window.core.repaint();

                        //game.dropDown();
                       // game.score += 1;
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        while(window.core.gameover()) {
            Thread.sleep(500);
            window.core.start();
        }

        JOptionPane.showConfirmDialog(null, "Your score: " + window.core.getScore(), "Game over", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);

         */
        //Conway code
        Window window = new Window("Conway's Game of Life");
        window.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        window.core.wellinit();
                        break;
                    case KeyEvent.VK_DOWN:
                        window.core.randomize();
                        window.core.repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        window.core.setField();
                        //System.out.println("key pressed" + window.core.getPosition());
                        break;
                    case KeyEvent.VK_RIGHT:
                        window.core.setField2();
                        break;
                    case KeyEvent.VK_SPACE:
                        window.core.setisRunning();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                window.core.setClickedCellColor((e.getY()-31)/(Constants.cellSize), (e.getX()-8)/(Constants.cellSize));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        Timer t = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.core.engine();
                window.core.repaint();
            }
        });
        t.start();
        /**
        while(true) {
            window.core.engine();
            window.core.repaint();
            Thread.sleep(100);
        }
         */
    }
}

package com.nth.ball.BallAnimation;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * Created by HAU TRUNG NGUYEN <haunt.hcm2015@gmail.com> on Nov 13, 2021
 */
public class BallAnimation extends JFrame {

    private Animation animation;
    private Engine engine;

    private BallAnimation() {
        engine = createEngine();
        animation = new Animation();
        addMouseListener(new MyMouseAdapter());
        setWindowProperties();
    }

    private Engine createEngine() {
        Engine engine = new Engine();
        Container cp = getContentPane();
        cp.add(engine);
        engine.setPreferredSize(new Dimension(Properties.WIDTH, Properties.HEIGHT));
        return engine;
    }

    private void setWindowProperties() {
        setResizable(false);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Ball Animation");
        setVisible(true);
    }

    private class Engine extends JPanel implements Runnable {

        private boolean running = false;

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            setBackground(Properties.BACK_COLOR);
            animation.paint(graphics);
        }

        @Override
        public void run() {

            long start = System.nanoTime();
            double elapsedTime = 0.0;
            double FPS = 60.0;

            while (true) {

                elapsedTime += ((System.nanoTime() - start) / 1000000000.0) * FPS;
                start = System.nanoTime();

                if (elapsedTime >= 1) {
                    animation.incrementPoints();
                    repaint();
                    elapsedTime--;
                }

                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    private class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);

            if (engine.running) {
                return;
            }

            Thread th = new Thread(engine);
            th.start();
            engine.running = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BallAnimation());
    }

}

package com.nth.ball.BallAnimation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

/**
 *
 * Created by HAU TRUNG NGUYEN <haunt.hcm2015@gmail.com> on Nov 13, 2021
 */
public class Animation {

    private final ArrayList<Point> listOfPoints;

    Animation() {
        listOfPoints = new ArrayList<>();
        generatePoints();
    }

    private void generatePoints() {
        for (int i = 0; i < Properties.POINTS; i++) {
            listOfPoints.add(new Point());
        }
    }

    void incrementPoints() {
        listOfPoints.forEach((p) -> p.increment());
    }

    void paint(Graphics g) {

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.clipRect(0, 0, Properties.WIDTH, Properties.HEIGHT);

        graphics.setColor(Properties.COLOR);

        listOfPoints.forEach((p) -> {
            graphics.fillOval(p.getX() - Properties.SIZE / 2, p.getY() - Properties.SIZE / 2, Properties.SIZE, Properties.SIZE);
        });
    }
}

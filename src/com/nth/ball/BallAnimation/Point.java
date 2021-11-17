package com.nth.ball.BallAnimation;

import java.util.Random;

/**
 *
 * Created by HAU TRUNG NGUYEN <haunt.hcm2015@gmail.com> on Nov 13, 2021
 */
public class Point {

    private int originX;
    private int originY;

    private double angle;

    private double radius;
    private double radiusIncrement;
    private int x;
    private int y;

    Point() {
        generateRandomPoint();
    }

    private void generateRandomPoint() {
        Random r = new Random();

        originX = Properties.WIDTH / 2;
        originY = Properties.HEIGHT / 2;

        angle = (2 * Math.PI) * r.nextDouble();

        radius = 0;
        radiusIncrement = r.nextDouble() * (double) r.nextInt(5);

        increment();
    }

    void increment() {
        radius += radiusIncrement;
        x = (int) (originX + (Math.cos(angle) * radius));
        y = (int) (originY + (Math.sin(angle) * radius));
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Origin X:\t\t\t" + originX + "\n");
        sb.append("Origin Y:\t\t\t" + originY + "\n");
        sb.append("Start Angle:\t\t" + angle + "\n");
        sb.append("Radius:\t" + radius + "\n");
        return new String(sb);
    }
}

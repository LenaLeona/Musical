package sandbox;

import graphicLib.G;
import graphicLib.Window;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Paint extends Window {

    static final int X = 400, Y = 200;
    public static int clicks = 0;
    //call the default constructor of the inner nested class
    public static Path thePath = new Path();
    public static Path.Pic thePic = new Path.Pic();

    public Paint() {
        super("Paint", 1000, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(G.rndColor());
        g.fillOval(100, 100, 200, 300);
        g.setColor(Color.BLACK);
        g.drawLine(100, 500, 500, 100);

        String salute = "dude, this is totally sweet!" + clicks;
        g.drawString(salute, X, Y);
        g.setColor(Color.RED);
        g.drawOval(X, Y, 2, 2);

        FontMetrics fm = g.getFontMetrics();
        int a = fm.getAscent(), d = fm.getDescent();
        int w = fm.stringWidth(salute);
        g.drawRect(X, Y - a, w, a + d);
        g.setColor(Color.BLACK);
        thePic.draw(g);
        g.setColor(Color.RED);
        thePath.draw(g); //g object can do the primitive thing
    }

    @Override
    public void mousePressed(MouseEvent me) {
        clicks++;
        thePath = new Path();
        thePath.add(me.getPoint());
        thePic.add(thePath);
        repaint(); //tells the OS I'm out of sync, refresh screen
    }

    public void mouseDragged(MouseEvent me) {
        thePath.add(me.getPoint());
        repaint();
    }

    //Path:build static nested innerclass: a way of changing the name of the outer class
    //nested class must have 'static'
    public static class Path extends ArrayList<Point> {

        //no need for constructor, just functions
        public void draw(Graphics g) {
            for (int i = 1; i < size(); i++) {
                //previous point and cur point
                Point p = get(i - 1), n = get(i);
                g.drawLine(p.x, p.y, n.x, n.y);
            }
        }

        //List: nested inside Path class (helper class, keep local as won't be use anywhere else)
        public static class Pic extends ArrayList<Path> {

            public void draw(Graphics g) {
                for (Path p : this) {
                    p.draw(g);
                }
            }
        }
    }
}

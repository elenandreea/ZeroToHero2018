package ro.teamnet.zerotohero.oop.graphicshape;

import java.lang.Exception;

public class Point {

    int xPos;
    int yPos;

    public Point(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {

            return false;
        }
        if(other instanceof Point){
            Point anotherPoint = (Point)other;
            if((xPos == anotherPoint.xPos) && (yPos == anotherPoint.yPos))
            return true;
        }
        return false;
    }

}
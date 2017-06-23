package ru.stga.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * Created by admin on 22.06.2017.
 */
public class PointTests {


    @Test
    public void testDistance() {
        Point p1 = new Point (5, 5);
        Point p2 = new Point(4, 2);
       assert Math.sqrt((((p1.x -p1.y)*(p1.x -p1.y))+((p2.x -p2.y)*(p2.x -p2.y)))) == 2;
    }
}


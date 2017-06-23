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
        Point p1 = new Point (5, 2);
        Point p2 = new Point(5, 4);
       Assert.assertEquals(p1.distance(p2), 2.0);
    }
}


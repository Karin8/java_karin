package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistancePositive() {
    Point p1 = new Point(6, 8);
    Point p2 = new Point(2, 9);
    Assert.assertEquals(p1.distance(p2), 4.123105625617661);
  }
}
package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistancePositive() {
    Point p = new Point(4, 5, 7, 9);
    Assert.assertEquals(p.distance(), 2.23606797749979);
  }

  @Test
  public void testDistanceNegative() {
    Point p = new Point(4, 5, 7, 9);
    Assert.assertEquals(p.distance(), 2.23606);
  }
}
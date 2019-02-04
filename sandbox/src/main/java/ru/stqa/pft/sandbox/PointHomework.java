package ru.stqa.pft.sandbox;

public class PointHomework {

  public static void main(String[] args) {

    Point p1 = new Point (6, 8);
    Point p2 = new Point(2, 9);

    System.out.println("Расстояние между точкой p1 с координатами (" + p1.x + ";" + p1.y + ") и точкой p2 с координатами (" + p2.x + ";" + p2.y + ") = " + p1.distance(p2));

  }
}
package edu.havrysh;

/*
  @author diana
  @project lab2
  @class Rectangle
  @version 1.0.0
  @since 20.03.25 - 12.42
*/

import java.util.Objects;

public class Rectangle {
    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle rectangle)) return false;
        return length == rectangle.length && width == rectangle.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }

    public int getPerimeter() {
        return 2 * (length + width);
    }

    public int getArea() {
        return length * width;
    }

    public boolean isSquare() {
        return length == width;
    }

    public void makeScale(int factor) {
        if (factor > 0) {
            this.length *= factor;
            this.width *= factor;
        }
    }

    public double getDiagonal() {
        return Math.sqrt(length * length + width * width);
    }

}

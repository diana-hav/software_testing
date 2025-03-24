package edu.havrysh;

/*
  @author diana
  @project lab2
  @class Rectangle
  @version 1.0.0
  @since 20.03.25 - 12.54
*/

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTest {

    Rectangle rectangle = new Rectangle(4, 5);

    @Test
    public void whenLength_4_andWidth_5_thenPerimeter_18() {
        int perimeter = rectangle.getPerimeter();
        assertEquals(18, perimeter);
    }

    @Test
    public void whenLength_4_andWidth_5_thenArea_20() {
        int area = rectangle.getArea();
        assertEquals(20, area);
    }

    @Test
    public void whenLength_4_andWidth_5_thenIsSquareFalse() {
        boolean result = rectangle.isSquare();
        assertFalse(result);
    }

    @Test
    public void whenLength_4_andWidth_5_thenDimensionsDoubled() {
        rectangle.makeScale(2);
        assertEquals(8, rectangle.getLength());
        assertEquals(10, rectangle.getWidth());
    }

    @Test
    public void whenLength_4_andWidth_5_thenDiagonal_6Point4() {
        double diagonal = rectangle.getDiagonal();
        assertEquals(6.403, diagonal, 0.001);
    }
}

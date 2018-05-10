/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.drawingtool;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author lucas
 */
public class DrawingToolTest {

    @Test
    public void shouldWriteHorizontalLineToCanvas() {
        /*
         Expected result:
         X  X  X     
         Y[ ][ ][ ]
         Y[x][x][x]   
         Y[ ][ ][ ]   
         */

        String[][] canvas = new String[3][3];
        int x1 = 0;
        int y1 = 1;
        int x2 = 2;
        int y2 = 1;

        DrawingTool.line(x1, y1, x2, y2, canvas);
        Assert.assertTrue(canvas[0][1].equals("x") && canvas[1][1].equals("x") && canvas[2][1].equals("x"));
    }

    @Test
    public void shouldWriteVerticalLineToCanvas() {
        /*
         Expected result:
         X  X  X     
         Y[ ][x][ ]
         Y[ ][x][ ]   
         Y[ ][x][ ]   
         */

        String[][] canvas = new String[3][3];
        int x1 = 1;
        int y1 = 0;
        int x2 = 1;
        int y2 = 2;

        DrawingTool.line(x1, y1, x2, y2, canvas);
        Assert.assertArrayEquals(new String[]{"x", "x", "x"}, canvas[1]);
    }

    @Test
    public void shouldWriteRectangleToCanvas() {
        /*
         Expected result:
         X  X  X  X  X  X
         Y[ ][ ][ ][ ][ ][ ]
         Y[ ][ ][ ][ ][ ][ ]   
         Y[ ][x][x][x][x][ ]   
         Y[ ][x][ ][ ][x][ ] 
         Y[ ][x][x][x][x][ ] 
         Y[ ][ ][ ][ ][ ][ ] 
         */

        String[][] canvas = new String[6][6];
        String[][] result = new String[6][6];
        result[1][2] = "x";
        result[1][3] = "x";
        result[1][4] = "x";

        result[2][2] = "x";
        result[2][4] = "x";

        result[3][2] = "x";
        result[3][4] = "x";

        result[4][2] = "x";
        result[4][3] = "x";
        result[4][4] = "x";

        int x1 = 1;
        int y1 = 2;
        int x2 = 4;
        int y2 = 4;

        DrawingTool.rect(x1, y1, x2, y2, canvas);
        Assert.assertArrayEquals(result, canvas);
    }

    @Test
    public void shoundNotAcceptX1OutOfBoundsLines() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.line(-1, 2, 2, 2, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundNotAcceptX2OutOfBoundsLines() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.line(1, 2, -1, 2, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundNotAcceptY1OutOfBoundsLines() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.line(1, -1, 1, 2, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundNotAcceptY2OutOfBoundsLines() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.line(1, 2, 1, -1, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundNotAcceptX1OutOfBoundsRectangle() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.rect(-1, 2, 2, 2, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundNotAcceptX2OutOfBoundsRectangle() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.rect(1, 2, 3, 2, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundNotAcceptY1OutOfBoundsRectangle() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.rect(1, -1, 1, 2, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundNotAcceptY2OutOfBoundsRectangle() {
        String[][] canvas = new String[3][3];
        try {
            DrawingTool.rect(1, 2, 1, 3, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundFillAreaInsideRectangle() {
        /*
         Expected result:
         X  X  X  X  X  X
         Y[ ][ ][ ][ ][ ][ ]
         Y[ ][ ][ ][ ][ ][ ]   
         Y[ ][x][x][x][x][ ]   
         Y[ ][x][o][o][x][ ] 
         Y[ ][x][x][x][x][ ] 
         Y[ ][ ][ ][ ][ ][ ] 
         */

        String[][] result = new String[6][6];
        String[][] canvas = new String[6][6];

        canvas[1][2] = "x";
        canvas[1][3] = "x";
        canvas[1][4] = "x";

        canvas[2][2] = "x";
        canvas[2][4] = "x";

        canvas[3][2] = "x";
        canvas[3][4] = "x";

        canvas[4][2] = "x";
        canvas[4][3] = "x";
        canvas[4][4] = "x";

        result[1][2] = "x";
        result[1][3] = "x";
        result[1][4] = "x";

        result[2][2] = "x";
        result[2][4] = "x";

        result[3][2] = "x";
        result[3][4] = "x";

        result[4][2] = "x";
        result[4][3] = "x";
        result[4][4] = "x";

        result[2][3] = "o";

        result[3][3] = "o";
        try {
            DrawingTool.fill(2, 3, "o", canvas);
            Assert.assertArrayEquals(result, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }

    @Test
    public void shoundFillAreaOutsideRectangle() {
        /*
         Expected result:
         X  X  X  X  X  X
         Y[o][o][o][o][o][o]
         Y[o][o][o][o][o][o]   
         Y[o][x][x][x][x][o]   
         Y[o][x][ ][ ][x][o] 
         Y[o][x][x][x][x][o] 
         Y[o][o][o][o][o][o] 
         */

        String[][] canvas = new String[6][6];
        String[][] result = new String[6][6];

        canvas[1][2] = "x";
        canvas[1][3] = "x";
        canvas[1][4] = "x";

        canvas[2][2] = "x";
        canvas[2][4] = "x";

        canvas[3][2] = "x";
        canvas[3][4] = "x";

        canvas[4][2] = "x";
        canvas[4][3] = "x";
        canvas[4][4] = "x";

        
        //Fill result with "o"
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                result[i][j] = "o";
            }
        }
        //Draw rectangle in result
        result[1][2] = "x";
        result[1][3] = "x";
        result[1][4] = "x";

        result[2][2] = "x";
        result[2][4] = "x";

        result[3][2] = "x";
        result[3][4] = "x";

        result[4][2] = "x";
        result[4][3] = "x";
        result[4][4] = "x";
        
        //Empty area inside rectangle
        result[2][3] = null;

        result[3][3] = null;
        try {
            DrawingTool.fill(0, 0, "o", canvas);
            Assert.assertArrayEquals(result, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }
    @Test
    public void shoundFillOpenAreas() {
        /*
         Expected result:
           X  X  X  X  X  X
         Y[o][o][o][o][o][o]
         Y[o][o][o][o][o][o]   
         Y[o][x][x][x][x][x]   
         Y[o][x][ ][ ][ ][ ] 
         Y[o][x][x][x][x][x] 
         Y[o][o][o][o][o][o] 
         */

        String[][] canvas = new String[6][6];
        String[][] result = new String[6][6];

        canvas[1][2] = "x";
        canvas[1][3] = "x";
        canvas[1][4] = "x";

        canvas[2][2] = "x";
        canvas[2][4] = "x";

        canvas[3][2] = "x";
        canvas[3][4] = "x";

        canvas[4][2] = "x";
        canvas[4][4] = "x";
        
        canvas[5][2] = "x";
        canvas[5][4] = "x";

        //Fill result with "o"
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                result[i][j] = "o";
            }
        }
        //Draw lines
        result[1][2] = "x";
        result[1][3] = "x";
        result[1][4] = "x";
        result[2][2] = "x";
        result[2][4] = "x";
        result[3][2] = "x";
        result[3][4] = "x";
        result[4][2] = "x";
        result[4][4] = "x";
        result[5][2] = "x";
        result[5][4] = "x";
        //Empty area inside lines
        result[2][3] = null;
        result[3][3] = null;
        result[3][3] = null;
        result[4][3] = null;
        result[5][3] = null;
        
        try {
            DrawingTool.fill(1, 1, "o", canvas);
            Assert.assertArrayEquals(result, canvas);
        } catch (RuntimeException ex) {
            Assert.assertSame("Point out of bounds", ex.getMessage());
        }

    }
}

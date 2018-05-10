/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.drawingtool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class DrawingTool {

    static void draw(int w, int h, String[][] canvas) {

        for (int j = -1; j <= h; j++) {
            for (int i = -1; i <= w; i++) {

                if (-1 < j && j < h) {
                    if (-1 < i && i < w) {
                        System.out.print(canvas[i][j] == null ? " " : canvas[i][j]);
                    } else {
                        System.out.print("|");
                    }
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }

    }

    public static String getDataToPrintWithMargin(int w, int h, String[][] canvas) {
        String s = "";
        for (int j = -1; j <= h; j++) {
            for (int i = -1; i <= w; i++) {

                if (-1 < j && j < h) {
                    if (-1 < i && i < w) {
                        s += canvas[i][j] == null ? " " : canvas[i][j];
                    } else {
                        s += "|";
                    }
                } else {
                    s += "-";
                }
            }
            s += "\n";
        }
        return s;
    }

    public static void rect(int x1, int y1, int x2, int y2, String[][] canvas) {
        if (x1 > x2 || y1 > y2) {
            throw new RuntimeException("Bad rectangle command");
        } else if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 >= canvas.length || x2 >= canvas.length || y1 >= canvas[0].length || y2 >= canvas[0].length) {
            throw new RuntimeException("Point out of bounds");
        } else {
            for (int i = x1; i <= x2; i++) {
                canvas[i][y1] = "x";
                canvas[i][y2] = "x";
            }
            for (int j = y1; j <= y2; j++) {
                canvas[x1][j] = "x";
                canvas[x2][j] = "x";
            }
        }
    }

    public static void line(int x1, int y1, int x2, int y2, String[][] canvas) {
        if (x1 != x2 && y1 != y2) {
            throw new RuntimeException("Bad line command");
        } else if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 >= canvas.length || x2 >= canvas.length || y1 >= canvas[0].length || y2 >= canvas[0].length) {
            throw new RuntimeException("Point out of bounds");
        } else {
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    canvas[i][j] = "x";
                }
            }
        }
    }

    public static void fill(int x, int y, String c, String[][] canvas) {
        if (x >= 0 && x < canvas.length && y >= 0 && y < canvas[0].length && !"x".equals(canvas[x][y]) && !c.equals(canvas[x][y])) {
            canvas[x][y] = c;
            fill(x - 1, y, c, canvas);
            fill(x + 1, y, c, canvas);
            fill(x, y - 1, c, canvas);
            fill(x, y + 1, c, canvas);
        }

    }

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        String[][] canvas;
        int h = 0;
        int w = 0;

        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            FileWriter fileWriter = new FileWriter(outputFileName);

            String[] lineBreak = scanner.nextLine().split(" ");

            //First command must be C - create canvas
            if (!"C".equals(lineBreak[0])) {
                throw new RuntimeException("Should create canvas first");
            } else {
                w = Integer.parseInt(lineBreak[1]);
                h = Integer.parseInt(lineBreak[2]);
                canvas = new String[w][h];
                fileWriter.append(getDataToPrintWithMargin(w, h, canvas));
            }

            //Command sequence
            while (scanner.hasNext()) {
                lineBreak = scanner.nextLine().split(" ");

                switch (lineBreak[0]) {
                    case "L":
                        int x1 = Integer.parseInt(lineBreak[1]) - 1;
                        int y1 = Integer.parseInt(lineBreak[2]) - 1;
                        int x2 = Integer.parseInt(lineBreak[3]) - 1;
                        int y2 = Integer.parseInt(lineBreak[4]) - 1;
                        line(x1, y1, x2, y2, canvas);
                        break;
                    case "R":
                        x1 = Integer.parseInt(lineBreak[1]) - 1;
                        y1 = Integer.parseInt(lineBreak[2]) - 1;
                        x2 = Integer.parseInt(lineBreak[3]) - 1;
                        y2 = Integer.parseInt(lineBreak[4]) - 1;
                        rect(x1, y1, x2, y2, canvas);
                        break;
                    case "B":
                        x1 = Integer.parseInt(lineBreak[1]) - 1;
                        y1 = Integer.parseInt(lineBreak[2]) - 1;
                        fill(x1, y1, lineBreak[3], canvas);
                        break;
                    default:
                        throw new RuntimeException("Unexpected command");

                }
                fileWriter.append(getDataToPrintWithMargin(w, h, canvas));
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package org.example;

public class Renderer {
    public void print(char[][] matrix) {
        System.out.println("   1     2     3");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < 3; col++) {
                System.out.print("| " + matrix[row][col] + " |" + " ");
            }
            System.out.println();
        }
    }
}

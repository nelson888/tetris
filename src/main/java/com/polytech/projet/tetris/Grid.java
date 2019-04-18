package com.polytech.projet.tetris;

public class Grid {

  private final int m; // Nombre de lignes
  private final int n; // Nombre de colonnes
  private int[][] grid;

  public Grid(int m, int n) {
    this.m = m;
    this.n = n;
    grid = new int[m][n];
  }

  public int get(int line, int col) {
    return grid[line][col];
  }

  public void set(int line, int col, int value) {
    if (line>=0 && line<this.grid.length && col>=0 && col<this.grid[0].length){
      this.grid[line][col] = value;
    } else {
      throw new IndexOutOfBoundsException(String.format("Index in out (%d, %d) of bound", line, col));
    }
  }

  public int getM() {
    return m;
  }

  public int getN() {
    return n;
  }
}

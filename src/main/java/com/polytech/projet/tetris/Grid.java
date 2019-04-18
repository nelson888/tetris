package com.polytech.projet.tetris;

public class Grid {

  private final int m;
  private final int n;
  private int[][] grid;

  public Grid(int m, int n) {
    this.m = m;
    this.n = n;
    grid = new int[m][n];
  }

  public int get(int i, int j) {
    return grid[i][j];
  }

  public void set(int i, int j, int value) {
    if (i>=0 && i<this.grid.length && j>=0 && j<this.grid[0].length){
      this.grid[i][j] = value;
    }
  }

  public int getM() {
    return m;
  }

  public int getN() {
    return n;
  }
}

package com.polytech.projet.tetris;

public class Grid {

  private final int n;
  private final int m;
  private int[][] grid;

  public Grid(int n, int m) {
    this.n = n;
    this.m = m;
    grid = new int[n][m];
  }

  public int get(int i, int j) {
    return grid[i][j];
  }

  public void set(int i, int j, int value){
    if (i>=0 && i<)
    this.grid[i][j] = value;
  }
}

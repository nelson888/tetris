package com.polytech.projet.tetris;

import java.util.Arrays;

public class Grid {

  private final int m; // Nombre de lignes
  private final int n; // Nombre de colonnes
  private Cell[][] grid;

  public Grid(int m, int n) {
    this.m = m;
    this.n = n;
    grid = new Cell[m][n];
    for (int i = 0; i < grid.length; i++) {
      Arrays.fill(grid[i], Cell.EMPTY);
    }
  }

  public Cell get(int line, int col) {
    return grid[line][col];
  }


  public Cell getSafe(int line, int col) {
    if (line < 0 || line >= getM() || col < 0 || col >= getN()) {
      return Cell.EMPTY;
    }
    return grid[line][col];
  }

  public void set(int line, int col, Cell value) {
    if (line>=0 && line<this.grid.length && col>=0 && col<this.grid[0].length){
      this.grid[line][col] = value;
    } else {
      throw new IndexOutOfBoundsException(String.format("Index in out (%d, %d) of bound", line, col));
    }
  }

  public Cell[] getLine(int l) {
    if (l < 0 || l >= getM()) {
      throw new IndexOutOfBoundsException("line " + l + " doesn't exist");
    }
    return grid[l];
  }

  public void setLine(int l, Cell[] cells) {
    if (cells.length != getN()) {
      throw new IllegalArgumentException("the line hasn't the right number of elements");
    }
    if (l < 0 || l >= getM()) {
      throw new IndexOutOfBoundsException("line " + l + " doesn't exist");
    }
    grid[l] = cells;
  }

  public int getM() {
    return m;
  }

  public int getN() {
    return n;
  }
}

package com.polytech.projet.tetris.grid;

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

  public Cell getSafely(int line, int col) {
    if (isOutOfBounds(line, col)) {
      return Cell.EMPTY;
    }
    return grid[line][col];
  }

  private boolean isOutOfBounds(int line, int col) {
    return line < 0 || line >= getM() || col < 0 || col >= getN();
  }

  public void set(int line, int col, Cell value) {
    if (isOutOfBounds(line, col)) {
      throw new IndexOutOfBoundsException(String.format("Index in out (%d, %d) of bound", line, col));
    }
    this.grid[line][col] = value;
  }

  public void setSafely(int line, int col, Cell value) {
    if (!isOutOfBounds(line, col)) {
      this.grid[line][col] = value;
    }
  }

  private boolean isLineOutOfBounds(int l) {
    return l < 0 || l >= getM();
  }

  public Cell[] getLine(int l) {
    if (isLineOutOfBounds(l)) {
      throw new IndexOutOfBoundsException("line " + l + " doesn't exist");
    }
    return grid[l];
  }

  public void setLine(int l, Cell[] cells) {
    if (cells.length != getN()) {
      throw new IllegalArgumentException("the line hasn't the right number of elements");
    }
    if (isLineOutOfBounds(l)) {
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

  public Grid copy() {
    Grid grid = new Grid(getM(), getN());
    for (int line = 0; line < getM(); line++) {
      grid.setLine(line, Arrays.copyOf(getLine(line), getN()));
    }
    return grid;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("(%d, %d)", m, n));
    for (int line = 0; line < getM(); line++) {
      builder.append('\n');
      for (int col = 0; col < getN(); col++) {
        builder.append(get(line, col))
          .append("\t");
      }
    }
    return builder.toString();
  }
}

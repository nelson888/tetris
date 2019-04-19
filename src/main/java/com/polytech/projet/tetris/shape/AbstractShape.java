package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.grid.Cell;
import com.polytech.projet.tetris.grid.Grid;

import java.util.function.BiFunction;

public abstract class AbstractShape implements Shape {

  protected Grid grid;
  private int l;
  private int c;

  public AbstractShape(Grid grid) {
    this.grid = grid;
  }

  public int getM() {
    return grid.getM();
  }


  public int getN() {
    return grid.getN();
  }

  public Grid getGrid() {
    return grid;
  }

  static Grid newShapeGrid(int n, int m, BiFunction<Integer, Integer, Boolean> initializer) {
    Grid grid = new Grid(m, n);
    for (int line = 0; line < grid.getM(); line++) {
      for (int col = 0; col < grid.getN(); col++) {
        grid.set(line, col, initializer.apply(line, col) ? Cell.FILLED : Cell.EMPTY);
      }
    }
    return grid;
  }

  @Override
  public int getColumn() {
    return c;
  }

  @Override
  public int getLine() {
    return l;
  }

  @Override
  public void setColumn(int c) {
    this.c = c;
  }

  @Override
  public void setLine(int l) {
    this.l = l;
  }

}

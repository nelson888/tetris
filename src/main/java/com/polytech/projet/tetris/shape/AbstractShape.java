package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Cell;
import com.polytech.projet.tetris.Grid;

import java.util.function.BiFunction;

public abstract class AbstractShape implements Shape {

  protected Grid grid;

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
    Grid grid = new Grid(n, m);
    for (int j = 0; j < grid.getM(); j++) {
      for (int i = 0; i < grid.getN(); i++) {
        grid.set(i, j, initializer.apply(i, j) ? Cell.FILLED : Cell.EMPTY);
      }
    }
    return grid;
  }

}

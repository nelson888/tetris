package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.data.Direction;
import com.polytech.projet.tetris.grid.Grid;

/**
 *   * *
 *   * *
 */
public class Square extends AbstractShape {

  private static final int SIZE = 2;
  private static final Grid SQUARE_GRID = newShapeGrid(SIZE, SIZE, (line, col) -> true);

  public Square() {
    super(SQUARE_GRID);
  }

  public void rotate(Direction direction) {
    //do nothing
  }
}

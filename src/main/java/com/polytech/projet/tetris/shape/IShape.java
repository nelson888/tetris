package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Direction;
import com.polytech.projet.tetris.Grid;

/**
 *   *
 *   *
 *   *
 *   *
 */
public class IShape extends AbstractShape {

  private static final int LENGTH = 4;
  private static final Grid INITIAL_GRID = newShapeGrid(1, LENGTH, (i, j) -> true);
  private static final Grid ROTATED_GRID = newShapeGrid(LENGTH, 1, (i, j) -> true);

  public IShape() {
    super(INITIAL_GRID);
  }

  public void rotate(Direction direction) {
    if (grid == INITIAL_GRID) {
      grid = ROTATED_GRID;
    } else {
      grid = INITIAL_GRID;
    }
  }

}

package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Direction;
import com.polytech.projet.tetris.Grid;

/**
 *     *
 *   * *
 *   *
 */
public class ZShape extends AbstractShape {

  private static final Grid INITIAL_GRID = newShapeGrid(2, 3, (i, j) -> i == 0 && j == 0 || i == 1 && j == 2);
  private static final Grid ROTATED_GRID = newShapeGrid(3, 2, (i, j) -> j == 0 && i == 0 || j == 1 && i == 2);


  public ZShape() {
    super(INITIAL_GRID);
  }

  @Override
  public void rotate(Direction direction) {
    if (grid == INITIAL_GRID) {
      grid = ROTATED_GRID;
    } else {
      grid = INITIAL_GRID;
    }
  }

}

package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Grid;

/**
 *   *
 *   *
 *   * *
 */
public class LShape extends RotatableShape {

  private static final Grid INITIAL_GRID = newShapeGrid(2, 3, (i, j) -> i == 0 || j == 2);
  private static final Grid ROTATED_GRID_1 = newShapeGrid(3, 2, (i, j) -> j == 0 || i == 0);
  private static final Grid ROTATED_GRID_2 = newShapeGrid(2, 3, (i, j) -> j == 1 || i == 0);
  private static final Grid ROTATED_GRID_3 = newShapeGrid(3, 2, (i, j) -> j == 0 || i == 2);

  private static final Grid[] GRIDS = new Grid[] {INITIAL_GRID, ROTATED_GRID_1, ROTATED_GRID_2, ROTATED_GRID_3};

  public LShape() {
    super(INITIAL_GRID);
  }

  @Override
  protected Grid getGridAt(int i) {
    return GRIDS[i];
  }

}

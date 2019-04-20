package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.grid.Grid;

/**
 * *
 * *
 * * *
 */
public class ReversedLShape extends AsymmetricShape {

  private static final Grid INITIAL_GRID = newShapeGrid(2, 3, (line, col) -> col == 1 || line == 2);
  private static final Grid ROTATED_GRID_1 = newShapeGrid(3, 2, (line, col) -> line == 1 || col == 0);
  private static final Grid ROTATED_GRID_2 = newShapeGrid(2, 3, (line, col) -> col == 0 || line == 0);
  private static final Grid ROTATED_GRID_3 = newShapeGrid(3, 2, (line, col) -> line == 0 || col == 2);

  private static final Grid[] GRIDS = new Grid[]{INITIAL_GRID, ROTATED_GRID_1, ROTATED_GRID_2, ROTATED_GRID_3};

  public ReversedLShape() {
    super(INITIAL_GRID);
  }

  @Override
  protected Grid getGridAt(int i) {
    return GRIDS[i];
  }

}

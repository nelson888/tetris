package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.grid.Grid;

/**
 *     *
 *   * *
 *   *
 */
public class ZShape extends SymetricShape {

  private static final Grid INITIAL_GRID = newShapeGrid(2, 3, (line, col) -> col == 0 && line != 0 || col == 1 && line != 2);
  private static final Grid ROTATED_GRID = newShapeGrid(3, 2, (line, col) -> line == 1 && col != 0 || line == 0 && col != 2);

  private static final Grid[] GRIDS = new Grid[] {INITIAL_GRID, ROTATED_GRID};

  public ZShape() {
    super(INITIAL_GRID);
  }

  @Override
  protected Grid getGridAt(int i) {
    return GRIDS[i];
  }

}

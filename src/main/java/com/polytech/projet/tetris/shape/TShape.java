package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Grid;

/**
 *   *
 *   * *
 *   *
 */
public class TShape extends RotatableShape {

  private static final Grid INITIAL_GRID = newShapeGrid(2, 3, (line, col) -> col == 0 || line == 1);
  private static final Grid ROTATED_1 = newShapeGrid(3, 2, (line, col) -> line == 0 || col == 1);
  private static final Grid ROTATED_2 =  newShapeGrid(2, 3, (line, col) -> col == 1 || line == 1);
  private static final Grid ROTATED_3 = newShapeGrid(3, 2, (line, col) -> line == 1 || col == 1);
  private static final Grid[] GRIDS = new Grid[] {INITIAL_GRID, ROTATED_1, ROTATED_2, ROTATED_3};

  public TShape() {
    super(INITIAL_GRID);
  }

  @Override
  protected Grid getGridAt(int i) {
    return GRIDS[i];
  }

}

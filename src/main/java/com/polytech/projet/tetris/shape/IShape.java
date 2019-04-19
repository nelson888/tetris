package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.grid.Grid;

/**
 *   *
 *   *
 *   *
 *   *
 */
public class IShape extends SymetricShape {

  private static final int LENGTH = 4;
  private static final Grid INITIAL_GRID = newShapeGrid(1, LENGTH, (line, col) -> true);
  private static final Grid ROTATED_GRID = newShapeGrid(LENGTH, 1, (line, col) -> true);

  private static final Grid[] GRIDS = new Grid[] {INITIAL_GRID, ROTATED_GRID};

  public IShape() {
    super(INITIAL_GRID);
  }


  @Override
  protected Grid getGridAt(int i) {
    return GRIDS[i];
  }
}

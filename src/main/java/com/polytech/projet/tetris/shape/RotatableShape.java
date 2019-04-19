package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Direction;
import com.polytech.projet.tetris.Grid;

public abstract class RotatableShape extends AbstractShape {

  private static final int POSSIBLE_ROTATIONS = 4;

  private int gridIndex;

  public RotatableShape(Grid grid) {
    super(grid);
    this.gridIndex = 0;
  }

  @Override
  public final void rotate(Direction direction) {
    gridIndex = (gridIndex + direction.toInt()) % POSSIBLE_ROTATIONS;
    if (gridIndex == -1) {
      gridIndex = 3;
    }
    grid = getGridAt(gridIndex);
  }

  protected abstract Grid getGridAt(int i);
}

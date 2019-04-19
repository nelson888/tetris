package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.data.Direction;
import com.polytech.projet.tetris.grid.Grid;

public abstract class RotatableShape extends AbstractShape {

  private static final int POSSIBLE_ROTATIONS = 4;

  private final int possibleRotations;
  private int gridIndex;

  public RotatableShape(Grid grid, int possibleRotations) {
    super(grid);
    this.gridIndex = 0;
    this.possibleRotations = possibleRotations;
  }

  @Override
  public final void rotate(Direction direction) {
    gridIndex = (gridIndex + direction.toInt()) % possibleRotations;
    if (gridIndex == -1) {
      gridIndex = possibleRotations - 1;
    }
    grid = getGridAt(gridIndex);
  }

  protected abstract Grid getGridAt(int i);
}

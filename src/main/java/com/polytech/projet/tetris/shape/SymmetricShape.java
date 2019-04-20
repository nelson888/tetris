package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.grid.Grid;

public abstract class SymmetricShape extends RotatableShape {

  public SymmetricShape(Grid grid) {
    super(grid, 2);
  }

}

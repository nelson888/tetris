package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.grid.Grid;

public abstract class SymetricShape extends RotatableShape {

  public SymetricShape(Grid grid) {
    super(grid, 2);
  }

}

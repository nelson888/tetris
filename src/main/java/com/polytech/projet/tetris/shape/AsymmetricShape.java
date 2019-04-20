package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.grid.Grid;

public abstract class AsymmetricShape extends RotatableShape {

  public AsymmetricShape(Grid grid) {
    super(grid, 4);
  }

}

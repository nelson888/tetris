package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Direction;
import com.polytech.projet.tetris.Grid;

public interface Shape {


  Grid getGrid();

  void rotate(Direction direction);

  int getN();

  int getM();

}

package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Direction;
import com.polytech.projet.tetris.Grid;

public interface Shape {


  Grid getGrid();

  void rotate(Direction direction);

  int getN();

  int getM();

  int getLine(); //le point en bas de la shape

  int getColumn(); //le point a gauche de la shape

  void setLine(int l);

  void setColumn(int c);
}

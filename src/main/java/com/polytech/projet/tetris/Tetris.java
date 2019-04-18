package com.polytech.projet.tetris;

public class Tetris extends Grid {

  public static final int FILLED = 1;
  public static final int EMPTY = 0;

  public Tetris() {
    super(10, 24);
  }

  public void nextFrame() {

  }

  private boolean isLineFilled(int j) {
    for (int i = 0; i < getN(); i++) {
      if (get(i, j) == EMPTY) {
        return false;
      }
    }
    return true;
  }
}

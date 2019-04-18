package com.polytech.projet.tetris;

public class Tetris extends Grid {

  public static final int FILLED = 1;
  public static final int EMPTY = 0;

  public Tetris() {
    super(24, 10);
  }

  public void nextFrame() {

  }

  private boolean isLineEmpty(int j){
      for(int i = 0; i< getN(); i++){
      }

      return false;
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

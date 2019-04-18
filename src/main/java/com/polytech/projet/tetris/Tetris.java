package com.polytech.projet.tetris;

public class Tetris extends Grid {

  public static final int FILLED = 1;
  public static final int EMPTY = 0;

  public Tetris() {
    super(24, 10);
  }

  public void nextFrame() {
      for(int i = 0; i< getN(); i++){
          if(isLineFilled(i)){
              emptyLine(i);
          }
      }
  }

  private boolean isLineEmpty(int i){
      for(int j = 0; j< getM();j++){
          if (get(i,j) == FILLED){
              return false;
          }
      }
      return true;
  }

  private boolean isLineFilled(int i) {
    for (int j = 0; j < getN(); j++) {
      if (get(i, j) == EMPTY) {
        return false;
      }
    }
    return true;
  }

  private void emptyLine(int i){
      for (int j = 0; j<getN(); j++){
          set(i,j,EMPTY);
      }
  }

  private void emptyCell(int i, int j){

  }
}

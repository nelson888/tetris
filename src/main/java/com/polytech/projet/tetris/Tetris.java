package com.polytech.projet.tetris;

public class Tetris extends Grid {

  public static final int FILLED = 1;
  public static final int EMPTY = 0;

  public Tetris() {
    super(10, 24);
  }

  public void nextFrame() {
      for(int i = getN()-1; i>=0; i--){
          if(isLineFilled(i)){
              dropEverythingFrom(i);
          }
          else if (isLinePartiallyFilled(i)){
              for (int j = i+1; j < getM(); j++) {
                  if (isLineEmpty(j)){

                  }
              }
          }
      }
  }

  private void dropEverythingFrom(int i) {
      for (int j = i-1; j>=0; j--) {

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

  private boolean isLinePartiallyFilled(int i){
      return !isLineEmpty(i) && !isLineFilled(i);
  }

  private void emptyLine(int i){
      for (int j = 0; j<getN(); j++){
          set(i,j,EMPTY);
      }
  }

  private void emptyCell(int i, int j){

  }
}

package com.polytech.projet.tetris;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.polytech.projet.tetris.Cell.EMPTY;
import static com.polytech.projet.tetris.Cell.FILLED;


public class Tetris extends Grid {

  private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  private static final String ANSI_RESET = "\u001B[0m";

  private static final String STRING = "  ";
  private static final Map<Cell, String> STRING_MAP;

  static {
    Map<Cell, String> map = new HashMap<>();
    map.put(Cell.EMPTY, STRING);
    map.put(Cell.FILLED, ANSI_WHITE_BACKGROUND + STRING + ANSI_RESET);
    STRING_MAP = Collections.unmodifiableMap(map);
  }

  public Tetris() {
    super(10, 24);
  }

  public void nextFrame() {
      for(int i = getM()-1; i>=0; i--){
          if(isLineFilled(i)){
              dropEverythingFrom(i);
          }

      }
  }

  private void dropEverythingFrom(int i) {
      for (int j = i-1; j>=0; j--) {
          setLine(j+1, getLine(j));
      }
      for (int j = 0; j < getN(); j++) {
          set(0,j,EMPTY);
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


  public void print() {
    for (int line = 0; line < getM(); line++) {
      for (int col = 0; col < getN(); col++) {
        System.out.print(STRING_MAP.get(get(line, col)));
      }
      System.out.println();
    }
  }
}

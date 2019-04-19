package com.polytech.projet.tetris;

import com.polytech.projet.tetris.shape.Shape;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.polytech.projet.tetris.Cell.EMPTY;
import static com.polytech.projet.tetris.Cell.FILLED;


public class Tetris extends Grid {

  private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  private static final String ANSI_RESET = "\u001B[0m";

  private static final String STRING = "  ";
  private static final Map<Cell, String> STRING_MAP;

  private static final String SHAPE_STRING = ANSI_BLUE_BACKGROUND + STRING + ANSI_RESET;
  static {
    Map<Cell, String> map = new HashMap<>();
    map.put(Cell.EMPTY, STRING);
    map.put(Cell.FILLED, ANSI_WHITE_BACKGROUND + STRING + ANSI_RESET);
    STRING_MAP = Collections.unmodifiableMap(map);
  }

  private Shape shape;

  public Tetris() {
    super(10, 24);
  }

  public void nextFrame(Direction dir) {
      for(int i = getM()-1; i>=0; i--){
          if(isLineFilled(i)){
              dropEverythingFrom(i);
          }
      }
      if (shape != null) {
        if (canFall(shape)) {
          shape.setLine(shape.getLine() + 1);
        } else {
          putInGrid(shape);
        }
      }
  }

  private boolean canFall(Shape shape) {
    if (shape.getLine() == getM()- 1) {
        return false;
    }

    shape.setLine(shape.getLine() + 1); //descend la shape et voit si il y a collision
    boolean collides = shapeCollides();
    shape.setLine(shape.getLine() - 1);
    return collides;
  }

  private boolean canMoveLeftRight(Direction dir){
      boolean collides;
      switch (dir){
          case LEFT:
              if (shape.getColumn()>= 1){
                  shape.setColumn(shape.getColumn()-1);
                  collides = shapeCollides();
                  shape.setColumn(shape.getColumn()+1);
              }
              else{
                  collides = true;
              }
              break;
          case RIGHT:
              if (shape.getColumn()+shape.getN()-2< getN()){
                  shape.setColumn(shape.getColumn()+1);
                  collides = shapeCollides();
                  shape.setColumn(shape.getColumn()-1);
              }
              else{
                  collides = true;
              }
              break;
          default:
              collides = false;
              break;
      }
      return collides;
  }

  private boolean shapeCollides() {
    Grid shapeGrid = shape.getGrid();
    for (int line = 0; line < shapeGrid.getM(); line++) {
      for (int col = 0; col < shapeGrid.getN(); col++) {
        Cell cell = shapeGrid.get(line, col);
        if (get(shape.getLine() - line, shape.getColumn() + col) == FILLED && cell == FILLED) {
          return false;
        }
      }
    }
    return true;
  }

  private void putInGrid(Shape shape) {
    Grid shapeGrid = shape.getGrid();
    for (int line = 0; line < shapeGrid.getM(); line++) {
      for (int col = 0; col < shapeGrid.getN(); col++) {
        Cell cell = shapeGrid.get(line, col);
        set(shape.getLine() - line, shape.getColumn() + col, cell);
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

  public void setShape(Shape shape) {
    this.shape = shape;
  }

  public void print() {
    for (int line = 0; line < getM(); line++) {
      for (int col = 0; col < getN(); col++) {
        if (isInShapeGrid(line, col)) {
          Grid shapeGrid = shape.getGrid();
          Cell cell = shapeGrid.get(line - shape.getLine(), col - shape.getColumn());
          System.out.print(cell == FILLED ? SHAPE_STRING : STRING_MAP.get(get(line, col)));
        }
        System.out.print(STRING_MAP.get(get(line, col)));
      }
      System.out.println();
    }
  }

  private boolean isInShapeGrid(int line, int col) {
    return line >= shape.getLine() && line < shape.getLine() + shape.getM() &&
      col >= shape.getColumn() && col < shape.getColumn() + shape.getN();
  }
}

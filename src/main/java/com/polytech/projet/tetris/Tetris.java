package com.polytech.projet.tetris;

import static com.polytech.projet.tetris.grid.Cell.EMPTY;
import static com.polytech.projet.tetris.grid.Cell.FILLED;
import static com.polytech.projet.tetris.grid.Cell.SHAPE;

import com.polytech.projet.tetris.data.Command;
import com.polytech.projet.tetris.data.Direction;
import com.polytech.projet.tetris.grid.Cell;
import com.polytech.projet.tetris.grid.Grid;
import com.polytech.projet.tetris.shape.Shape;
import com.polytech.projet.tetris.shape.ShapeFactory;

public class Tetris extends Grid {

  public static final int WIDTH = 10;
  public static final int HEIGHT = 24;

  private final ShapeFactory shapeFactory;
  private Shape shape;
  private boolean gameOver;

  public Tetris() {
    super(HEIGHT, WIDTH);
    shapeFactory = new ShapeFactory();
    shape = shapeFactory.createRandomShape();
    gameOver = false;
  }

  public void nextFrame(Command command) {
    removeFilledLines();
    switch (command) {
      case IDLE:
        shapeDown();
        break;
      case LEFT:
        if (canMoveLeftRight(shape, command.toDirection())) {
          shape.setColumn(shape.getColumn() - 1);
        } else {
          shapeDown();
        }
        break;
      case RIGHT:
        if (canMoveLeftRight(shape, command.toDirection())) {
          shape.setColumn(shape.getColumn() + 1);
        } else {
          shapeDown();
        }
        break;
      case ROTATE_LEFT:
        if (canRotate(shape, Direction.LEFT)) {
          shape.rotate(Direction.LEFT);
        } else {
          shapeDown();
        }
        break;
      case ROTATE_RIGHT:
        if (canRotate(shape, Direction.RIGHT)) {
          shape.rotate(Direction.RIGHT);
        } else {
          shapeDown();
        }
        break;
      case FAST_DOWN:
        Shape shape = this.shape;
        while (this.shape == shape) { //while there isn't a new shape created
          nextFrame(Command.IDLE);
        }
        break;
    }
  }

  private void shapeDown() {
    if (shape != null) {
      if (canFall(shape)) {
        shape.setLine(shape.getLine() + 1);
      } else {
        putInGrid(shape);
        gameOver = gameOver || shape.getLine() - shape.getM() < 0;
        if (!gameOver) {
          shape = shapeFactory.createRandomShape();
        }
      }
    }
  }

  private void removeFilledLines() {
    int i = getM() - 1;
    while (i >= 0) {
      if (isLineFilled(i)) {
        dropEverythingFrom(i);
      } else {
        i--;
      }
    }
  }

  private boolean canFall(Shape shape) {
    if (shape.getLine() == getM() - 1) {
      return false;
    }
    shape.setLine(shape.getLine() + 1); //descend la shape et voit si il y a collision
    boolean collides = shapeNotColliding();
    shape.setLine(shape.getLine() - 1);
    return collides;
  }

  private boolean canMoveLeftRight(Shape shape, Direction dir) {
    boolean canMove = false;
    switch (dir) {
      case LEFT:
        if (shape.getColumn() >= 1) {
          shape.setColumn(shape.getColumn() - 1);
          canMove = shapeNotColliding();
          shape.setColumn(shape.getColumn() + 1);
        }
        break;
      case RIGHT:
        if (shape.getColumn() + shape.getN() < getN()) {
          shape.setColumn(shape.getColumn() + 1);
          canMove = shapeNotColliding();
          shape.setColumn(shape.getColumn() - 1);
        }
        break;
    }
    return canMove;
  }

  private boolean canRotate(Shape shape, Direction direction) {
    shape.rotate(direction);
    boolean canRotate = shapeNotColliding();
    shape.rotate(direction.opposite());
    return canRotate;
  }

  private boolean shapeNotColliding() {
    Grid shapeGrid = shape.getGrid();
    for (int line = 0; line < shapeGrid.getM(); line++) {
      for (int col = 0; col < shapeGrid.getN(); col++) {
        Cell cell = shapeGrid.get(line, col);
        if (cell == FILLED && getSafely(shape.getLine() - line, shape.getColumn() + col) == FILLED) {
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
        if (cell != EMPTY) {
          setSafely(shape.getLine() - line, shape.getColumn() + col, cell);
        }
      }
    }
  }

  private void dropEverythingFrom(int i) {
    for (int j = i - 1; j >= 0; j--) {
      setLine(j + 1, getLine(j));
    }
    for (int j = 0; j < getN(); j++) {
      set(0, j, EMPTY);
    }
  }

  private boolean isLineFilled(int i) {
    for (int j = 0; j < getN(); j++) {
      if (get(i, j) == EMPTY) {
        return false;
      }
    }
    return true;
  }

  public void setShape(Shape shape) {
    this.shape = shape;
  }

  public Grid getPrintGrid() {
    Grid printGrid = copy();
    if (shape != null) {
      Grid shapeGrid = shape.getGrid();
      for (int line = 0; line < shapeGrid.getM(); line++) {
        for (int col = 0; col < shapeGrid.getN(); col++) {
          Cell cell = shapeGrid.get(line, col);
          if (cell == FILLED) {
            printGrid.setSafely(shape.getLine() - line, shape.getColumn() + col, SHAPE);
          }
        }
      }
    }
    return printGrid;
  }

  public void print() {
    Grid printGrid = getPrintGrid();
    for (int line = 0; line < getM(); line++) {
      System.out.print(line + "\t|");
      for (int col = 0; col < getN(); col++) {
        System.out.print(printGrid.get(line, col).consoleString());
      }
      System.out.println();
    }

    System.out.print(" \t ");
    for (int i = 0; i < getN(); i++) {
      System.out.print("__");
    }
    System.out.println();
    System.out.print(" \t ");
    for (int i = 0; i < getN(); i++) {
      String n = String.valueOf(i);
      if (n.length() == 1) {
        n += " ";
      }
      System.out.print(n);
    }
    System.out.println();
  }

  public boolean isGameOver() {
    return gameOver;
  }
}

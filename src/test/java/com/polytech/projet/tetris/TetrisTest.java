package com.polytech.projet.tetris;

import static org.junit.Assert.assertEquals;
import static com.polytech.projet.tetris.Cell.FILLED;
import static com.polytech.projet.tetris.Cell.EMPTY;

import com.polytech.projet.tetris.shape.Shape;
import com.polytech.projet.tetris.shape.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TetrisTest {

  private Tetris tetris;

  private static final Cell[] FILLED_LINE;

  static {
    FILLED_LINE = new Cell[24];
    Arrays.fill(FILLED_LINE, FILLED);
  }

  @Before
  public void init() {
    tetris = new Tetris();
  }

  @Test
  public void emptyGridTest() {
    for (int line = 0; line < tetris.getM(); line++) {
      for (int col = 0; col < tetris.getN(); col++) {
        assertEquals("Should be empty", EMPTY, tetris.get(line, col));
      }
    }
  }

  @Test
  public void lineFilledTest() {
    int line = 4;
    for (int col = 0; col < tetris.getN(); col++) {
      tetris.set(line, col, FILLED);
    }
    tetris.nextFrame(Direction.DOWN);
    for (int col = 0; col < tetris.getN(); col++) {
      assertEquals("Should be equal", Cell.EMPTY, tetris.get(line, col));
    }
  }

  @Test
  public void lineNotEntirelyFilledTest() {
    Cell[] line = new Cell[tetris.getN()];
    for (int col = 0; col < tetris.getN(); col++) {
      line[col] = col > 0 ? FILLED : EMPTY; //not entirely filled line
    }

    int l = 4;
    for (int col = 0; col < tetris.getN(); col++) {
      tetris.set(l, col, line[col]);
    }

    tetris.nextFrame(Direction.DOWN);

    for (int col = 0; col < tetris.getN(); col++) {
      assertEquals("Should be equal", line[col], tetris.get(l, col));
    }
  }

  @Test
  public void lineFilledUnderLineNotEntirelyFilled() {
    Cell[] line = new Cell[tetris.getN()];
    for (int col = 0; col < tetris.getN(); col++) {
      line[col] = col > 0 ? FILLED : EMPTY; //not entirely filled line
    }

    int l =  tetris.getM() - 2; //avant derniere ligne
    for (int col = 0; col < tetris.getN(); col++) {
      tetris.set(l, col, line[col]);
    }
    tetris.setLine(l + 1, FILLED_LINE);

    tetris.nextFrame(Direction.DOWN);

    for (int col = 0; col < tetris.getN(); col++) {
      assertEquals("Should be equal", line[col], tetris.get(l + 1, col));
    }
  }

  @Test
  public void shapeFallTest() {
    Shape shape = new Square();
    tetris.setShape(shape);
    tetris.nextFrame(Direction.DOWN);
    assertEquals("Should have went down", 1, shape.getLine());
    assertEquals("Shouldn't have moved", 0, shape.getColumn());
  }

  @Test
  public void shapeCanGoLeftTest(){
    Shape shape = new Square();
    int column = 1;
    shape.setColumn(column);
    tetris.setShape(shape);
    tetris.nextFrame(Direction.LEFT);
    assertEquals("Should've went left",column - 1,shape.getColumn());
    assertEquals("Shouldn'thave moved", 0,shape.getLine());
  }

  @Test
  public void shapeCannotGoLeftTest(){
    Shape shape = new Square();
    shape.setColumn(0);
    tetris.setShape(shape);
    tetris.nextFrame(Direction.LEFT);
    assertEquals("Shouldn'thave moved",0,shape.getColumn());
    assertEquals("Shouldn'thave moved", 0,shape.getLine());
  }

  @Test
  public void shapeInGridTest() { //when shape cannot fall, it is stored in grid
    Shape shape = new Square();
    shape.setLine(tetris.getM() - 1);
    int column = 4;
    shape.setColumn(column);
    tetris.setShape(shape);
    tetris.nextFrame(Direction.DOWN);

    assertEquals("Should have went down", tetris.getM() - 1, shape.getLine());
    assertEquals("Shouldn't have moved", column, shape.getColumn());
    for (int line = 0; line < shape.getM(); line++) {
      for (int col = 0; col < shape.getN(); col++) {
        assertEquals("Should have went down", tetris.get(shape.getLine() - line, shape.getColumn() + col), FILLED);
      }
    }
  }
}

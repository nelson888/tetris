package com.polytech.projet.tetris;

import static com.polytech.projet.tetris.grid.Cell.FILLED;
import static com.polytech.projet.tetris.grid.Cell.EMPTY;
import static org.junit.Assert.*;

import com.polytech.projet.tetris.data.Command;
import com.polytech.projet.tetris.grid.Cell;
import com.polytech.projet.tetris.shape.Shape;
import com.polytech.projet.tetris.shape.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TetrisTest {

  private static final Cell[] FILLED_LINE;

  static {
    FILLED_LINE = new Cell[Tetris.WIDTH];
    Arrays.fill(FILLED_LINE, FILLED);
  }

  private Tetris tetris;

  @Before
  public void init() {
    tetris = new Tetris();
    tetris.setShape(null);
  }

  @Test
  public void emptyGridTest() {
    for (int line = 0; line < tetris.getM(); line++) {
      for (int col = 0; col < tetris.getN(); col++) {
        assertEquals("Should be empty", EMPTY, tetris.get(line, col));
      }
    }
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void lineFilledTest() {
    int line = 4;
    for (int col = 0; col < tetris.getN(); col++) {
      tetris.set(line, col, FILLED);
    }
    tetris.nextFrame(Command.IDLE);
    for (int col = 0; col < tetris.getN(); col++) {
      assertEquals("Should be equal", Cell.EMPTY, tetris.get(line, col));
    }
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void almostAllLinesFilled() {
    for (int line = 1; line < tetris.getM(); line++) {
      tetris.setLine(line, FILLED_LINE);
    }
    tetris.nextFrame(Command.IDLE);

    for (int line = 0; line < tetris.getM(); line++) {
      for (int col = 0; col < tetris.getN(); col++) {
        assertEquals("Should be empty", EMPTY, tetris.get(line, col));
      }
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

    tetris.nextFrame(Command.IDLE);

    for (int col = 0; col < tetris.getN(); col++) {
      assertEquals("Should be equal", line[col], tetris.get(l, col));
    }
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void lineFilledUnderLineNotEntirelyFilled() {
    Cell[] line = new Cell[tetris.getN()];
    for (int col = 0; col < tetris.getN(); col++) {
      line[col] = col > 0 ? FILLED : EMPTY; //not entirely filled line
    }

    int l = tetris.getM() - 2; //avant derniere ligne
    for (int col = 0; col < tetris.getN(); col++) {
      tetris.set(l, col, line[col]);
    }
    tetris.setLine(l + 1, FILLED_LINE);

    tetris.nextFrame(Command.IDLE);

    for (int col = 0; col < tetris.getN(); col++) {
      assertEquals("Should be equal", line[col], tetris.get(l + 1, col));
    }
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void shapeFallTest() {
    Shape shape = new Square();
    tetris.setShape(shape);
    tetris.nextFrame(Command.IDLE);
    assertEquals("Should have went down", 1, shape.getLine());
    assertEquals("Shouldn't have moved", 0, shape.getColumn());
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void shapeCanGoLeftTest() {
    Shape shape = new Square();
    int column = 1;
    shape.setColumn(column);
    tetris.setShape(shape);
    tetris.nextFrame(Command.LEFT);
    assertEquals("Should've went left", column - 1, shape.getColumn());
    assertEquals("Shouldn'thave moved", 0, shape.getLine());
  }

  @Test
  public void shapeCannotGoLeftTest() {
    Shape shape = new Square();
    shape.setColumn(0);
    tetris.setShape(shape);
    tetris.nextFrame(Command.LEFT);
    assertEquals("Shouldn'thave moved", 0, shape.getColumn());
    assertEquals("Should have fell", 1, shape.getLine());
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void shapeCanGoRightTest() {
    Shape shape = new Square();
    shape.setColumn(tetris.getN() - (1 + shape.getN()));
    tetris.setShape(shape);
    tetris.nextFrame(Command.RIGHT);

    assertEquals("Should've moved", tetris.getN() - shape.getN(), shape.getColumn());
    assertEquals("Shouldn't have moved", 0, shape.getLine());
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void shapeCannotGoRightTest() {
    Shape shape = new Square();
    shape.setColumn(tetris.getN() - shape.getN());
    tetris.setShape(shape);
    tetris.nextFrame(Command.RIGHT);

    assertEquals("Shouldn't have moved", tetris.getN() - shape.getN(), shape.getColumn());
    assertEquals("Should have fell", 1, shape.getLine());
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void shapeInGridTest() { //when shape cannot fall, it is stored in grid
    Shape shape = new Square();
    shape.setLine(tetris.getM() - 1);
    int column = 4;
    shape.setColumn(column);
    tetris.setShape(shape);
    tetris.nextFrame(Command.IDLE);

    assertEquals("Should have went down", tetris.getM() - 1, shape.getLine());
    assertEquals("Shouldn't have moved", column, shape.getColumn());
    for (int line = 0; line < shape.getM(); line++) {
      for (int col = 0; col < shape.getN(); col++) {
        assertEquals("Should have went down", tetris.get(shape.getLine() - line, shape.getColumn() + col), FILLED);
      }
    }
    assertFalse("Shouldn't have lost", tetris.isGameOver());
  }

  @Test
  public void lostTest() {
    tetris.setShape(new Square());
    for (int line = 1; line < tetris.getM(); line++) {
      for (int col = 0; col < tetris.getN() - 1; col++) { //not full line
        tetris.set(line, col, FILLED);
      }
    }
    tetris.nextFrame(Command.IDLE);

    assertTrue("Should have lost", tetris.isGameOver());
  }
}

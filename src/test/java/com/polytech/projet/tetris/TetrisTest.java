package com.polytech.projet.tetris;

import static org.junit.Assert.assertEquals;
import static com.polytech.projet.tetris.Cell.FILLED;
import static com.polytech.projet.tetris.Cell.EMPTY;

import org.junit.Before;
import org.junit.Test;

public class TetrisTest {

  private Tetris tetris;

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
    tetris.nextFrame();
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

    tetris.nextFrame();

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
      tetris.set(l + 1, col, FILLED);
    }

    tetris.nextFrame();

    for (int col = 0; col < tetris.getN(); col++) {
      assertEquals("Should be equal", line[col], tetris.get(l + 1, col));
    }
  }
}

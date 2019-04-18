package com.polytech.projet.tetris;

import static org.junit.Assert.assertEquals;
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
    for (int j = 0; j < tetris.getM(); j++) {
      for (int i = 0; i < tetris.getN(); i++) {
        assertEquals("Should be empty", Tetris.EMPTY, tetris.get(i, j));
      }
    }
  }

  public void lineFilledTest() {

  }

  public void lineNotEntirelyFilledTest() {

  }

  public void lineFilledUnderLineNotEntirelyFilled() {

  }
}

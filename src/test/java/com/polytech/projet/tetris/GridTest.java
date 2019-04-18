package com.polytech.projet.tetris;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GridTest {

  @Test
  public void emptyGridTest(){
    Grid testGrid = new Grid(2,2);
    for (int i = 0; i<testGrid.getM(); i++){
        for (int j = 0; j<testGrid.getN();j++){
            assertEquals(Cell.EMPTY, testGrid.get(i,j));
        }
    }
  }

  @Test
  public void setOneCell(){
    Grid testGrid  = new Grid(1,1);
    testGrid.set(0,0, Cell.FILLED);
    assertEquals(Cell.FILLED, testGrid.get(0,0));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void setCellTooBig(){
    Grid testGrid  = new Grid(1,1);
    testGrid.set(10,0, Cell.FILLED);
  }


  @Test
  public void getLineTest(){
    Grid testGrid  = new Grid(2,2);
    for (int i = 0; i < 2; i++) {
      testGrid.set(1, i, Cell.FILLED);
    }
    assertArrayEquals("Should be equal", new Cell[]{Cell.FILLED, Cell.FILLED}, testGrid.getLine(1));
  }

  @Test
  public void setLineTest(){
    Grid testGrid  = new Grid(2,2);
    testGrid.setLine(1, new Cell[]{Cell.FILLED, Cell.FILLED});
    assertArrayEquals("Should be equal", new Cell[]{Cell.FILLED, Cell.FILLED}, testGrid.getLine(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void setLineTooShortTest(){
    Grid testGrid  = new Grid(2,2);
    testGrid.setLine(1, new Cell[]{Cell.FILLED});
    assertArrayEquals("Should be equal", new Cell[]{Cell.FILLED, Cell.FILLED}, testGrid.getLine(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void setLineNonExistingest(){
    Grid testGrid  = new Grid(2,2);
    testGrid.setLine(21, new Cell[]{Cell.FILLED, Cell.FILLED});
    assertArrayEquals("Should be equal", new Cell[]{Cell.FILLED, Cell.FILLED}, testGrid.getLine(1));
  }

}

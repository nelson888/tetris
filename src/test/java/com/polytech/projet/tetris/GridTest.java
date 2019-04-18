package com.polytech.projet.tetris;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GridTest {

  @Test
  public void emptyGridTest(){
    Grid testGrid = new Grid(2,2);
    for (int i = 0; i<testGrid.getM(); i++){
        for (int j = 0; j<testGrid.getN();j++){
            assertEquals(0,testGrid.get(i,j));
        }
    }
  }

  @Test
  public void setOneCell(){

  }

}

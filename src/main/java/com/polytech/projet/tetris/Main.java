package com.polytech.projet.tetris;

public class Main {



  public static void main(String[] args) {
    Tetris tetris = new Tetris();

    for (int i = 0; i < tetris.getN(); i++) {
      tetris.set(tetris.getM() - 1, i, Tetris.FILLED);
    }
    tetris.set(tetris.getM() - 2, 0, Tetris.FILLED);

    TetrisPrinter.print(tetris);
  }
}

package com.polytech.projet.tetris;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TetrisPrinter {

  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  public static final String ANSI_RESET = "\u001B[0m";

  private static final String STRING = "  ";
  private static final Map<Integer, String> STRING_MAP;

  static {
    Map<Integer, String> map = new HashMap<>();
    map.put(Tetris.EMPTY, STRING);
    map.put(Tetris.FILLED, ANSI_WHITE_BACKGROUND + STRING + ANSI_RESET);
    STRING_MAP = Collections.unmodifiableMap(map);
  }

  public static void print(Tetris tetris) {
    for (int line = 0; line < tetris.getM(); line++) {
      for (int col = 0; col < tetris.getN(); col++) {
        System.out.print(STRING_MAP.get(tetris.get(line, col)));
      }
      System.out.println();
    }
  }
}

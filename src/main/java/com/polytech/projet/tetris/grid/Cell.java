package com.polytech.projet.tetris.grid;

import java.awt.*;

public enum Cell {
  FILLED {
    @Override
    public String consoleString() {
      return ANSI_WHITE_BACKGROUND + STRING + ANSI_RESET;
    }

    @Override
    public Color color() {
      return Color.gray;
    }
  },
  EMPTY {
    @Override
    public String consoleString() {
      return STRING;
    }

    @Override
    public Color color() {
      return Color.black;
    }
  },
  SHAPE { //for printing
    @Override
    public String consoleString() {
      return ANSI_BLUE_BACKGROUND + STRING + ANSI_RESET;
    }

    @Override
    public Color color() {
      return Color.white;
    }
  };

  private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String STRING = "  ";

  public abstract String consoleString();
  public abstract Color color();
}

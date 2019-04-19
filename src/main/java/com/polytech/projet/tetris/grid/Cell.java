package com.polytech.projet.tetris.grid;

public enum Cell {
  FILLED {
    @Override
    public String consoleString() {
      return ANSI_WHITE_BACKGROUND + STRING + ANSI_RESET;
    }
  },
  EMPTY {
    @Override
    public String consoleString() {
      return STRING;
    }
  },
  SHAPE { //for printing
    @Override
    public String consoleString() {
      return ANSI_BLUE_BACKGROUND + STRING + ANSI_RESET;
    }
  };

  private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String STRING = "  ";

  public abstract String consoleString();
}

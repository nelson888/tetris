package com.polytech.projet.tetris;

public enum Direction {
  LEFT {
    @Override
    public int toInt() {
      return -1;
    }

    @Override
    public Direction opposite() {
      return RIGHT;
    }
  }, RIGHT {
    @Override
    public int toInt() {
      return 1;
    }

    @Override
    public Direction opposite() {
      return LEFT;
    }
  }, DOWN {
    @Override
    public int toInt() { return 0; }

    @Override
    public Direction opposite() { return null; }
  };



  public abstract int toInt();
  public abstract Direction opposite();

}

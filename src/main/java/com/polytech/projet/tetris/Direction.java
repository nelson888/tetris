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
  };



  public abstract int toInt();
  public abstract Direction opposite();

}

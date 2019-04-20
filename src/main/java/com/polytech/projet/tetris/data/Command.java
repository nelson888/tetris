package com.polytech.projet.tetris.data;

public enum Command {
  LEFT {
    @Override
    public Direction toDirection() {
      return Direction.LEFT;
    }
  },
  RIGHT {
    @Override
    public Direction toDirection() {
      return Direction.RIGHT;
    }
  },
  ROTATE_LEFT, ROTATE_RIGHT, IDLE, FAST_DOWN;

  public Direction toDirection() {
    throw new RuntimeException("Cannot convert " + this + " to a direction");
  }
}

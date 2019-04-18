package com.polytech.projet.tetris.shape;

import com.polytech.projet.tetris.Direction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class ShapeFactory {

  private static final Map<Integer, Supplier<Shape>> SHAPE_SUPPLIER_MAP;
  private static final int NB_SHAPES;

  static {
    Map<Integer, Supplier<Shape>> map = new HashMap<>();
    int nbShapes = 0;
    map.put(nbShapes++, IShape::new);
    map.put(nbShapes++, Square::new);
    map.put(nbShapes++, TShape::new);
    map.put(nbShapes++, SShape::new);
    map.put(nbShapes++, ZShape::new);
    map.put(nbShapes++, LShape::new);
    map.put(nbShapes++, ReversedLShape::new);

    SHAPE_SUPPLIER_MAP = Collections.unmodifiableMap(map);
    NB_SHAPES = nbShapes;
  }

  private final Random shapeRandom = new Random();
  private final Random rotateRandom = new Random();

  public Shape createRandomShape() {
    Shape shape = SHAPE_SUPPLIER_MAP.get(shapeRandom.nextInt(NB_SHAPES)).get();
    for (int i = rotateRandom.nextInt(3); i >=0; i--) {
      shape.rotate(Direction.RIGHT);
    }
    return shape;
  }

}

package com.polytech.projet.tetris;

import com.polytech.projet.tetris.shape.Square;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {



  public static void main(String[] args) throws InterruptedException {
    Tetris tetris = new Tetris();
    tetris.setShape(new Square());


    System.out.println("Bienvenue dans Tetris. Voici les commandes");
    System.out.println("Q pour bouger a gauche");
    System.out.println("s pour bouger en bas");
    System.out.println("D pour bouger a droite");
    System.out.println();

    tetris.print();

    AtomicReference<Direction> directionReference = new AtomicReference<>(Direction.DOWN);
    Executor executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> {
      try (Scanner scanner = new Scanner(System.in)) {
        switch (scanner.next().toUpperCase().charAt(0)) {
          case 'Q':
            directionReference.set(Direction.LEFT);
            break;
          case 'S':
            directionReference.set(Direction.DOWN);
          case 'D':
            directionReference.set(Direction.RIGHT);
        }
      }
    });
    while (true) {
      Thread.sleep(1000L);

      System.out.println(directionReference.get());
      tetris.nextFrame(directionReference.get());
      System.out.println();
      System.out.println();
      tetris.print();
      System.out.println();
    }

  }
}

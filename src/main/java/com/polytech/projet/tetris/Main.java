package com.polytech.projet.tetris;

import com.polytech.projet.tetris.data.Command;
import com.polytech.projet.tetris.shape.LShape;
import com.polytech.projet.tetris.shape.ReversedLShape;
import com.polytech.projet.tetris.shape.SShape;
import com.polytech.projet.tetris.shape.TShape;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Tetris tetris = new Tetris();
    tetris.setShape(new SShape());


    System.out.println("Bienvenue dans Tetris. Voici les commandes");
    System.out.println("\tQ pour bouger a gauche");
    System.out.println("\ts pour bouger en bas");
    System.out.println("\tD pour bouger a droite");
    System.out.println("Apres chaque commande, appuyer sur entree pour qu'elle soit prise en compte");
    System.out.println();

    tetris.print();

    AtomicReference<Command> directionReference = new AtomicReference<>(Command.DOWN);
    Executor executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> {
      try (Scanner scanner = new Scanner(System.in)) {

        while (true) {
        switch (scanner.next().toUpperCase().charAt(0)) {
          case 'Q':
            directionReference.set(Command.LEFT);
            break;
          case 'S':
            directionReference.set(Command.DOWN);
            break;
          case 'D':
            directionReference.set(Command.RIGHT);
            break;
          case 'A':
            directionReference.set(Command.ROTATE_LEFT);
            break;
          case 'E':
            directionReference.set(Command.ROTATE_RIGHT);
            break;
        }}
      }
    });
    while (true) {
      directionReference.set(Command.DOWN);
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

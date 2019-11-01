package com.polytech.projet.tetris;

import com.polytech.projet.tetris.data.Command;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

  private static final long DELTA_TIME = 400; // in ms

  public static void main(String[] args) throws InterruptedException {
    Tetris tetris = new Tetris();

    System.out.println("Bienvenue dans Tetris. Voici les commandes");
    System.out.println("\tQ pour bouger a gauche");
    System.out.println("\tS pour bouger une forme tout en bas");
    System.out.println("\tD pour bouger a droite");
    System.out.println("\tP pour quitter le jeu");
    System.out.println("Apres chaque commande, appuyer sur Entree pour qu'elle soit prise en compte");
    System.out.println();

    tetris.print();

    AtomicReference<Command> commandReference = new AtomicReference<>(Command.IDLE);
    AtomicReference<Boolean> quitReference = new AtomicReference<>(false);
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> promptCommands(tetris, commandReference, quitReference));
    while (!quitReference.get() && !tetris.isGameOver()) {
      commandReference.set(Command.IDLE);
      Thread.sleep(DELTA_TIME);
      System.out.println(commandReference.get());
      tetris.nextFrame(commandReference.get());
      System.out.println();
      tetris.print();
      System.out.println();
    }
    if (tetris.isGameOver()) {
      System.out.println("Tu as perdu!!!");
    } else {
      System.out.println("Jeu quitté");
    }
    executor.shutdown();
  }

  private static void promptCommands(Tetris tetris, AtomicReference<Command> directionReference, AtomicReference<Boolean> quit) {
    try (Scanner scanner = new Scanner(System.in)) {
      while (!quit.get() && !tetris.isGameOver()) {
        String next = scanner.next();
        if (next.isEmpty()) {
          continue;
        }
        switch (next.toUpperCase().charAt(0)) {
          case 'Q':
            directionReference.set(Command.LEFT);
            break;
          case 'S':
            directionReference.set(Command.FAST_DOWN);
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
          case 'P':
            quit.set(true);
            break;
        }
      }
    }
  }

}

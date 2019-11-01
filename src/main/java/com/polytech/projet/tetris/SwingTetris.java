package com.polytech.projet.tetris;

import com.polytech.projet.tetris.data.Command;
import com.polytech.projet.tetris.grid.Grid;
import com.tambapps.utils.swingutils.jpanel.PixelJPanel;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Toolkit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SwingTetris extends JFrame implements KeyListener {

  private final PixelJPanel panel;
  private final transient Tetris tetris;
  private Runnable onExit;

  public SwingTetris(Tetris tetris, Dimension screenSize) {
    this.tetris = tetris;
    panel = new PixelJPanel(tetris.getN(), tetris.getM());
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    add(panel);
    update();
    int height = Math.round(screenSize.height * 0.75f);
    float wFactor = ((float)tetris.getN()) / ((float) tetris.getM());
    int width = Math.round(height * wFactor);
    setSize(width, height);

    setTitle("TETRIS");
    setLocationRelativeTo(null);
    addCOnCloseListener();
  }

  private void addCOnCloseListener() {
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        onExit.run();
        System.exit(0);
      }
    });
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

  }
  public static void main(String[] args) {
    Tetris tetris = new Tetris();
    SwingTetris swingTetris = new SwingTetris(tetris, Toolkit.getDefaultToolkit().getScreenSize());
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    long delay = 500;
    ScheduledFuture future = executor.scheduleAtFixedRate(() -> {
        if (!tetris.isGameOver()) {
          tetris.nextFrame(Command.IDLE);
          swingTetris.update();
        }
      },
      delay, delay, TimeUnit.MILLISECONDS);
    swingTetris.onExit = () -> {
      future.cancel(true);
      executor.shutdownNow();
    };

    EventQueue.invokeLater(() -> {
      swingTetris.setVisible(true);
    });
  }

  public void update() {
    Grid printGrid = tetris.getPrintGrid();
    for (int j = 0; j < printGrid.getM(); j++) {
      for (int i = 0; i < printGrid.getN(); i++) {
        panel.setPixelAt(i, j, printGrid.get(j, i).color());
      }
    }
    panel.redrawPixels();
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        tetris.nextFrame(Command.LEFT);
        break;
      case KeyEvent.VK_RIGHT:
        tetris.nextFrame(Command.RIGHT);
        break;
      case KeyEvent.VK_DOWN:
        tetris.nextFrame(Command.IDLE);
        break;
      case KeyEvent.VK_SPACE:
        tetris.nextFrame(Command.ROTATE_LEFT);
        break;
      case KeyEvent.VK_ENTER:
        tetris.nextFrame(Command.FAST_DOWN);
        break;
    }
    update();
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {

  }
}

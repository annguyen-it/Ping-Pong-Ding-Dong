package main.java.mvc.game.board;

import main.java.mvc.game.GameController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameAdapter extends KeyAdapter {

    private final GameController controller;

    public GameAdapter(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        controller.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        controller.keyReleased(e);
    }
}

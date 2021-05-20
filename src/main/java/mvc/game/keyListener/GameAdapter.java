package main.java.mvc.game.keyListener;

import main.java.mvc.game.GameController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class is used to listen to the player's interaction with computer, then execute function as they want
 */
public class GameAdapter extends KeyAdapter {

    /**
     * {@code GameController} of Game
     */
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

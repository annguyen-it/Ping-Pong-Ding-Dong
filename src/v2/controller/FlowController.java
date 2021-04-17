package v2.controller;

import v2.Game;
import v2.model.Model;
import v2.view.View;

import javax.swing.*;

public class FlowController {

    private final JLayeredPane layer;
    private Controller<? extends View, ? extends Model> controller;

    public FlowController(JLayeredPane layer) {
        this.layer = layer;
        controller = new MenuController(this);
    }

    public JLayeredPane getLayer() {
        return layer;
    }

    public void init(){
        setup();
    }

    public void switchController(Controller<? extends View, ? extends Model> controller) {
        this.controller = controller;
        setup();
    }

    private void setup() {
        setupUI();
        drawUI();
        setupEventListener();
    }

    private void setupUI() {
        View view = controller.view;

        layer.removeAll();

        view.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);

        layer.add(view);
        layer.repaint();
    }

    private void drawUI() {
        controller.drawUI();
    }

    public void setupEventListener(){
        controller.initEvent();
    }
}

package main.java.mvc.common.flow;

import main.java.App;
import main.java.mvc.common.Controller;
import main.java.mvc.common.Model;
import main.java.mvc.common.View;
import main.java.mvc.menu.MenuController;

import javax.swing.*;

public class FlowController {

    private final JLayeredPane layer;
    private Controller<? extends View, ? extends Model> controller;

    public FlowController(JLayeredPane layer) {
        this.layer = layer;
        controller = new MenuController(this);
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
        View view = controller.getView();

        layer.removeAll();

        view.setBounds(0, 0, App.WIDTH, App.HEIGHT);

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

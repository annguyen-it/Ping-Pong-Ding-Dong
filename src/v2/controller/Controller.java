package v2.controller;

import v2.Game;
import v2.model.MenuModel;
import v2.model.Model;
import v2.view.MenuView;
import v2.view.View;

import javax.swing.*;

public class Controller {

    private View view;
    private Model model;
    private final JLayeredPane layer;

    public Controller(JLayeredPane layer) {
        this.layer = layer;
    }

    public void setup() {
        setView();
        this.model = new MenuModel(this);
    }

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public JLayeredPane getLayer() {
        return layer;
    }

    public void setView(){
        view = new MenuView(this);
        view.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);

        layer.add(view);
        layer.repaint();

        drawUI();
    }

    public void setView(View view) {
        layer.removeAll();

        view.setBounds(0, 0, Game.WIDTH, Game.HEIGHT);
        this.view = view;

        layer.add(view);
        layer.repaint();

        drawUI();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void drawUI() {
        view.initUI();
        view.initEvent();
    }
}

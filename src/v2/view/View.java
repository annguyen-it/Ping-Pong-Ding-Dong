package v2.view;

import v2.controller.Controller;

import javax.swing.*;

public abstract class View extends JLabel {

    protected final Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public abstract void initUI();

    public abstract void initEvent();
}

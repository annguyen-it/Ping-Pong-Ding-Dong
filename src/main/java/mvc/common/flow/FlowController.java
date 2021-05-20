package main.java.mvc.common.flow;

import main.java.App;
import main.java.mvc.common.Controller;
import main.java.mvc.common.Model;
import main.java.mvc.common.View;
import main.java.mvc.menu.MenuController;

import javax.swing.*;

/**
 * Class {@code FlowController} is used to control flow of program.
 * <p>
 * There is only one FlowController instance is used during program's life time
 * </p>
 */
public class FlowController {

    /**
     * The layer that program should use to paint UI on it. Everything display on screen must be add on this.
     */
    private final JLayeredPane layer;

    /**
     * Used to control "MVC block"'s controller. It will decide which "MVC block" is focused at the moment.
     */
    private Controller<? extends View, ? extends Model> controller;

    /**
     * Initialize a newly crated {@code FlowController} object so that it manage the same layer as the argument.
     * Then, initialize controller as new {@code MenuController}, because Menu should be the first thing that user sees very first.
     *
     * @param layer A {@code JLayeredPane}.
     */
    public FlowController(JLayeredPane layer) {
        this.layer = layer;
        controller = new MenuController(this);
    }

    /**
     * Initialize first state of {@code FlowController}.
     * <p>
     * It just calls to setup(), do to some stuff to setup program's UI and logics
     * </p>
     */
    public void init() {
        setup();
    }

    /**
     * This function is called when user want to navigate to other "MVC block" (i.e: Navigate from Menu to Game).
     * <p>
     * This function assign current controller to the new controller that is passed as argument.
     * Then calls to setup() to setup necessary stuff.
     * </p>
     *
     * @param controller A {@code Controller}
     */
    public void switchController(Controller<? extends View, ? extends Model> controller) {
        this.controller = controller;
        setup();
    }

    /**
     * Setup necessary things to display and let user controls the program.
     */
    private void setup() {
        setupUI();
        drawUI();
        setupEventListener();
    }

    /**
     * Prepare to draw the UI
     */
    private void setupUI() {
        View view = controller.getView();

        layer.removeAll();

        view.setBounds(0, 0, App.WIDTH, App.HEIGHT);

        layer.add(view);
        layer.repaint();
    }

    /**
     * Draw the UI that match with current controller
     */
    private void drawUI() {
        controller.drawUI();
    }

    /**
     * Setup event listener so that program can listen to user's actions
     */
    public void setupEventListener() {
        controller.initEvent();
    }
}

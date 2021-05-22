package main.java.mvc.common;

import main.java.mvc.common.flow.FlowController;

/**
 * Class {@code Controller} is used to control the "MVC block"
 * <p>
 * There is only one Controller is available at a moment in program. And that one must be managed by {@code FlowController}
 * </p>
 *
 * @param <V> An implementation of {@code View}
 * @param <M> An implementation of {@code Model}
 *
 * @implNote {@code V} and {@code M} must be in the same "MVC block"
 */
public abstract class Controller<V extends View, M extends Model> {

    /**
     * A {@code FlowController} instance that manages this controller
     */
    protected final FlowController flowController;

    /**
     * An instance of {@code View}'s implementation
     */
    protected final V view;

    /**
     * An instance of {@code Model}'s implementation
     */
    protected final M model;

    /**
     * Initializes newly created {@code Controller} object with {@code FlowController}, {@code View} and {@code Model}
     *
     * @param flowController An instance of {@code FlowController}
     * @param view           An instance of {@code View}
     * @param model          An instance of {@code Model}
     */
    public Controller(FlowController flowController, V view, M model) {
        this.flowController = flowController;
        this.view = view;
        this.model = model;
    }

    public V getView() {
        return view;
    }

    public M getModel() {
        return model;
    }

    /**
     * Initializes events fire in "MVC Block" when user interactive with program.
     */
    public abstract void initEvent();

    /**
     * Draw the UI in {@code View}
     */
    public void drawUI() {
        view.initUI();
    }

    /**
     * Switch to other "MVC block" by request to flowController to switch to the new controller
     *
     * @param newController A {@code Controller}}
     *
     * @implNote New controller's class must be different from current controller's class
     */
    protected void switchController(Controller<? extends View, ? extends Model> newController) {
        assert this.getClass() !=
               newController.getClass() : "New controller's class must be different from current controller's class";

        flowController.switchController(newController);
    }
}

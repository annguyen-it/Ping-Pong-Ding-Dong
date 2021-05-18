package main.java.mvc.common;

import main.java.mvc.common.flow.FlowController;

public abstract class Controller<V extends View, M extends Model> {

    protected final FlowController flowController;

    private final V view;
    private final M model;

    public Controller(FlowController flowController, V view, M model) {
        this.flowController = flowController;
        this.view = view;
        this.model = model;
    }

    public abstract void initEvent();

    public V getView() { return view; }

    public M getModel() { return model; }

    public void drawUI() {
        view.initUI();
    }

    protected void switchController(Controller<? extends View, ? extends Model> newController) {
        flowController.switchController(newController);
    }
}

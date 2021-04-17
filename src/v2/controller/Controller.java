package v2.controller;

import v2.model.Model;
import v2.view.View;

public abstract class Controller<V extends View, M extends Model> extends IController {

    protected final FlowController flowController;

    protected final V view;
    protected final M model;

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

    protected void drawUI() {
        view.initUI();
    }

    protected void switchController(Controller<? extends View, ? extends Model> newController) {
        flowController.switchController(newController);
    }

    public abstract void initEvent();
}

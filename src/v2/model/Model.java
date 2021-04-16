package v2.model;

import v2.controller.Controller;
import v2.view.View;

public abstract class Model {

    protected Controller<? extends View, ? extends Model> controller;

    public Model() {
        controller = null;
    }

    public void setController(Controller<? extends View, ? extends Model> controller) {
        this.controller = controller;
    }
}

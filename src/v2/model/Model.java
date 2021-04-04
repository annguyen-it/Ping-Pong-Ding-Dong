package v2.model;

import v2.controller.Controller;

public abstract class Model {

    protected final Controller controller;

    public Model(Controller controller) {
        this.controller = controller;
    }
}

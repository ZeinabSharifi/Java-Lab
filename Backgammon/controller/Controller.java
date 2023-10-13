package controller;

import model.Model;

public class Controller {
    private final Model model ;
	//changed

    public Controller(Model model) {
        this.model = model ;
    }
////////////added line

    public Model getModel() {
        return model;
    }
}

package pl.bernat.controller;

import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public abstract class BaseController {
    protected ViewFactory viewFactory;
    private String fxmlName;

    public String getFxmlName() {
        return fxmlName;
    }

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }
}
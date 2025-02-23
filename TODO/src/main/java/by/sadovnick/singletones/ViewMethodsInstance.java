package by.sadovnick.singletones;

import by.sadovnick.handlers.ViewMethods;
import by.sadovnick.handlers.impl.ViewMethodsImpl;

public class ViewMethodsInstance {

    private static ViewMethodsInstance INSTANCE;
    private final ViewMethods viewMethods;

    private ViewMethodsInstance() {
        this.viewMethods = new ViewMethodsImpl();
    }

    public static ViewMethodsInstance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ViewMethodsInstance();
        }
        return INSTANCE;
    }

    public ViewMethods getViewMethods() {
        return viewMethods;
    }
}

package by.sadovnick.singletones;

import by.sadovnick.view.OutputView;
import by.sadovnick.view.impl.OutputViewImpl;

public class OutPutViewInstance {

    private static OutPutViewInstance INSTANCE;
    private final OutputView outputView;

    private OutPutViewInstance() {
        this.outputView = new OutputViewImpl();
    }

    public static OutPutViewInstance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OutPutViewInstance();
        }
        return INSTANCE;
    }

    public OutputView getOutputView() {
        return outputView;
    }
}

package by.sadovnick.view.impl;

import by.sadovnick.view.OutputView;

public class OutputViewImpl implements OutputView {

    @Override
    public void outputView(String text) {
        System.out.println(text);
    }
}

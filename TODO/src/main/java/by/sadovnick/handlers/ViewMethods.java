package by.sadovnick.handlers;

import by.sadovnick.view.Messages;

import java.io.IOException;

public interface ViewMethods {

    void startProgram() throws IOException;

    String questionCreateTask(Messages messages) throws IOException;

    String outputTask() throws IOException;

    String outputAllTasks() throws IOException;
}

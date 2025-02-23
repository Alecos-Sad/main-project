package by.sadovnick.handlers.impl;

import by.sadovnick.controller.NodeModelService;
import by.sadovnick.controller.parse.ParseNoteModel;
import by.sadovnick.handlers.InputTextHandler;
import by.sadovnick.handlers.ViewMethods;
import by.sadovnick.model.NoteModel;
import by.sadovnick.singletones.BufferedReaderInstance;
import by.sadovnick.singletones.NodeModeServiceInstance;
import by.sadovnick.singletones.OutPutViewInstance;
import by.sadovnick.view.Messages;
import by.sadovnick.view.OutputView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ViewMethodsImpl implements ViewMethods {

    private final BufferedReader reader = BufferedReaderInstance.getInstance().getReader();
    private final OutputView view = OutPutViewInstance.getInstance().getOutputView();

    @Override
    public void startProgram() throws IOException {
        while (true) {
            view.outputView(Messages.START_MESSAGE.getValue());
            new InputTextHandler().handler();
        }
    }

    @Override
    public String questionCreateTask(Messages messages) throws IOException {
        String readerValue;
        do {
            view.outputView(messages.getValue());
            readerValue = reader.readLine();
            if (Messages.QUESTION_OF_NUMBER.equals(messages) && !readerValue.matches("\\d+")) {
                view.outputView("value must be number but: " + readerValue);
            } else {
                break;
            }
        } while (true);
        return readerValue;
    }

    @Override
    public String outputTask() throws IOException {
        String readerValue;
        do {
            readerValue = reader.readLine();
            if (!readerValue.matches("\\d+")) {
                view.outputView("value must be number but: " + readerValue);
            } else {
                break;
            }
        } while (true);
        return readerValue;
    }

    @Override
    public String outputAllTasks() throws IOException {
        NodeModelService nodeModelService = NodeModeServiceInstance.getInstance().getNodeModelService();
        File[] files = new File(ParseNoteModel.PATH_FILES).listFiles();
        StringBuilder builder = new StringBuilder();
        List<String> filenames = Arrays.stream(Objects.requireNonNull(files))
                .map(file -> file.getName().substring(0, file.getName().lastIndexOf(".")))
                .toList();
        for (String filename : filenames) {
            NoteModel loadModel = nodeModelService.load(Integer.parseInt(filename));
            String result = "Порядковый номер записи - %s, Заголовок - %s".formatted(filename, loadModel.getTitle());
            builder.append(result).append("\n");
        }
        return builder.toString();
    }
}

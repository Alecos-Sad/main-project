package by.sadovnick.handlers;

import by.sadovnick.controller.NodeModelService;
import by.sadovnick.model.NoteModel;
import by.sadovnick.singletones.BufferedReaderInstance;
import by.sadovnick.singletones.NodeModeServiceInstance;
import by.sadovnick.singletones.OutPutViewInstance;
import by.sadovnick.singletones.ViewMethodsInstance;
import by.sadovnick.view.OutputView;

import java.io.BufferedReader;
import java.io.IOException;

import static by.sadovnick.view.Messages.*;

public class InputTextHandler {

    private final BufferedReader reader = BufferedReaderInstance.getInstance().getReader();
    private final NodeModelService nodeModelService = NodeModeServiceInstance.getInstance().getNodeModelService();
    private final OutputView output = OutPutViewInstance.getInstance().getOutputView();
    private final ViewMethods viewMethods = ViewMethodsInstance.getInstance().getViewMethods();

    public void handler() throws IOException {
        switch (reader.readLine()) {
            case "C", "c" -> nodeModelService.save(nodeModelService.create());
            case "A", "a" -> output.outputView(OUTPUT_MESSAGE_TASKS.getValue()
                    .formatted(viewMethods.outputAllTasks()));
            case "U", "u" -> {
                output.outputView(OUTPUT_MESSAGE_UPDATE_TASKS.getValue()
                        .formatted(viewMethods.outputAllTasks()));
                nodeModelService.update(Integer.parseInt(reader.readLine()));
            }
            case "D", "d" -> {
                output.outputView(OUTPUT_MESSAGE_DELETE_TASKS.getValue()
                        .formatted(viewMethods.outputAllTasks()));
                nodeModelService.delete(Integer.parseInt(reader.readLine()));
            }
            case "V", "v" -> {
                output.outputView(OUTPUT_MESSAGE_READ_TASK.getValue()
                        .formatted(viewMethods.outputAllTasks()));
                NoteModel noteModel = nodeModelService.load(Integer.parseInt(viewMethods.outputTask()));
                output.outputView(OUTPUT_TASK_TEXT.getValue()
                        .formatted(
                                noteModel.getNumber(),
                                noteModel.getDate(),
                                noteModel.getTitle(),
                                noteModel.getText()
                        ));
            }
            default -> output.outputView("Invalid input");
        }
    }
}

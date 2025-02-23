package by.sadovnick.controller.impl;

import by.sadovnick.controller.NodeModelService;
import by.sadovnick.controller.parse.ParseNoteModel;
import by.sadovnick.handlers.ViewMethods;
import by.sadovnick.model.NoteModel;
import by.sadovnick.singletones.BufferedReaderInstance;
import by.sadovnick.singletones.OutPutViewInstance;
import by.sadovnick.singletones.ViewMethodsInstance;
import by.sadovnick.view.OutputView;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.sadovnick.view.Messages.*;

public class NodeModelServiceImpl implements NodeModelService {

    private final BufferedReader reader = BufferedReaderInstance.getInstance().getReader();
    private final ViewMethods viewMethods = ViewMethodsInstance.getInstance().getViewMethods();
    private final ParseNoteModel parseNoteModel = new ParseNoteModel();
    private final OutputView output = OutPutViewInstance.getInstance().getOutputView();

    @Override
    public NoteModel create() throws IOException {
        return NoteModel.builder()
                .number(Integer.parseInt(viewMethods.questionCreateTask(QUESTION_OF_NUMBER)))
                .date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .title(viewMethods.questionCreateTask(QUESTION_OF_TITLE))
                .text(viewMethods.questionCreateTask(QUESTION_OF_TEXT))
                .build();
    }

    @Override
    public void save(NoteModel noteModel) throws IOException {
        parseNoteModel.parseNoteModel(noteModel);
    }

    @Override
    public NoteModel load(int number) throws IOException {
        return parseNoteModel.unparseNoteModel(number);
    }

    @Override
    public NoteModel update(int number) throws IOException {
        NoteModel noteModelUpdated = requestUpdate(load(number));
        delete(number);
        save(noteModelUpdated);
        return noteModelUpdated;
    }

    @Override
    public void delete(int number) throws IOException {
        Files.delete(Path.of(ParseNoteModel.PATH_FILES + number + ".txt"));
        output.outputView("Запись с номером %d успешно удалена!".formatted(number));
    }

    private NoteModel requestUpdate(NoteModel noteModel) throws IOException {
        output.outputView(
                """
                        Что вы хотите изменить?
                        1. Заголовок
                        2. Текст записи
                        
                        Введите номер желаемого изменения
                        """);
        int number = Integer.parseInt(reader.readLine());

        switch (number) {
            case 1 -> {
                output.outputView("Введите измененный заголовок:");
                noteModel.setTitle(reader.readLine());
                output.outputView("Заголовок успешно изменен.");
            }
            case 2 -> {
                output.outputView("Введите измененный текст:");
                noteModel.setText(reader.readLine());
                output.outputView("Текст успешно изменен.");
            }
            default -> System.out.println("Нет такой позиции!");
        }
        return noteModel;
    }
}

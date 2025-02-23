package by.sadovnick.controller.parse;

import by.sadovnick.model.NoteModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParseNoteModel {

    public final static String PATH_FILES = "D:\\MainProject\\main-project\\TODO\\src\\main\\resources\\";

    public void parseNoteModel(NoteModel noteModel) throws IOException {
        if (!checkFile(noteModel.getNumber())) {
            String filePath = PATH_FILES + noteModel.getNumber() + ".txt";
            Files.createFile(Path.of(filePath));
            Files.writeString(Path.of(filePath),
                    """
                            Порядковый номер:   %d
                            Дата создания:      %s
                            
                            Заголовок:          %s
                            Текст записи:       %s
                            """.formatted(
                            noteModel.getNumber(),
                            noteModel.getDate(),
                            noteModel.getTitle(),
                            noteModel.getText()));
        }
    }

    public NoteModel unparseNoteModel(int number) throws IOException {
        if (checkFile(number)) {
            List<String> lines = Files.readAllLines(Path.of(PATH_FILES + number + ".txt"));

            Map<String, String> parseLinesMap = lines
                    .stream()
                    .map(line -> line.split(":", 2))
                    .filter(split -> split.length == 2)
                    .collect(Collectors.toMap(
                            split -> split[0].trim(),
                            split -> split[1].trim()
                    ));

            return NoteModel.builder()
                    .number(Integer.parseInt(parseLinesMap.get("Порядковый номер")))
                    .date(parseLinesMap.get("Дата создания"))
                    .title(parseLinesMap.get("Заголовок"))
                    .text(parseLinesMap.get("Текст записи"))
                    .build();
        }
        return NoteModel.builder().build();
    }

    public Boolean checkFile(int number) {
        return Files.exists(Path.of(PATH_FILES + number + ".txt"));
    }
}

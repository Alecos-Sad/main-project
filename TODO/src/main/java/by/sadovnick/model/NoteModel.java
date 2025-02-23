package by.sadovnick.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteModel {

    private int number;
    private String date;
    private String title;
    private String text;
}

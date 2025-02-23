package by.sadovnick.controller;

import by.sadovnick.model.NoteModel;

import java.io.IOException;

public interface NodeModelService {

    NoteModel create() throws IOException;

    void save(NoteModel noteModel) throws IOException;

    NoteModel load(int number) throws IOException;

    NoteModel update(int number) throws IOException;

    void delete(int number) throws IOException;
}

package by.sadovnick.view;

public enum Messages {

    START_MESSAGE("""
            ********************************
            Вам доступны следующие команды:*
            ********************************
            Создать запись           "C"   *
            Просмотреть все записи   "A"   *
            Просмотреть запись       "V"   *
            Изменить запись          "U"   *
            Удалить запись           "D"   *
            ********************************
            """),
    QUESTION_OF_NUMBER("""
            Ведите порядковый номер записи:
            """),
    QUESTION_OF_TITLE("""
            Ведите заголовок записи:
            """),
    QUESTION_OF_TEXT("""
            Ведите текст напоминания:
            """),
    OUTPUT_MESSAGE_TASKS("""
            Ваши сохраненные записи:
            %s
            """),
    OUTPUT_MESSAGE_UPDATE_TASKS("""
            Какую запись желаете изменить?
            Введите порядковый номер записи:
            %s
            """),
    OUTPUT_MESSAGE_DELETE_TASKS("""
            Какую запись желаете удалить:
            %s
            """),
    OUTPUT_MESSAGE_READ_TASK("""
            Какую запись желаете просмотреть?
            Введите порядковый номер записи:
            %s
            """),
    OUTPUT_TASK_TEXT("""
            Информация по вашей выбранной записи:
            Порядковый номер - %s
            Дата создания -    %s
            Заголовок -        %s
            Текст -            %s
            """);

    private final String value;

    Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

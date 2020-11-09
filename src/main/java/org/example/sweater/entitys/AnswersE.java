package org.example.sweater.entitys;
import org.springframework.data.annotation.Id;

public class AnswersE {
    //private String Id;
    private String content;

    public AnswersE() {}

    public AnswersE(String content1) {
        this.content = content1;
    }

    @Override
    public String toString() {
        return String.format(
                "AnswerE [content='%s']",content);
    }
}
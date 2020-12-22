import org.example.sweater.entitys.AnswersE;
import org.example.sweater.entitys.QuestionE;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionETest{
    @Test
    public void addAnswerTest(){
        QuestionE questionE = new QuestionE("First", "second");
        questionE.addAnswer(new AnswersE("Third"));
        List<AnswersE> Answers = new ArrayList<AnswersE>();
        Answers.add(new AnswersE("Third"));
        assertEquals(questionE.getList().get(0).toString(), Answers.get(0).toString());
    }
    @Test
    public void getShortcontent(){
        QuestionE questionE = new QuestionE("First", "second");
        assertEquals(questionE.getShortcontent(),"First");
    }
    @Test
    public void getContent(){
        QuestionE questionE = new QuestionE("First", "second");
        assertEquals(questionE.getContent(),"second");
    }
}

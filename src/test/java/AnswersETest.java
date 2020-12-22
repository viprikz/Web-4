import org.example.sweater.entitys.AnswersE;
import org.example.sweater.entitys.QuestionE;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnswersETest {
    @Test
    public void getinformationTest(){
        AnswersE answersE = new AnswersE("Third");
        assertEquals(answersE.getinformation(), "Third");
    }
}

package org.example.sweater.entitys;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.sweater.entitys.AnswersE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
@JsonAutoDetect
public class QuestionE {
    @Id
    private String id;

    private String shortcontent;
    private String content;
    private int order;
    private List<AnswersE> Answers = new ArrayList<AnswersE>();

    public QuestionE() {}

    public QuestionE(String shortcontent, String content, int order) {
        this.shortcontent = shortcontent;
        this.content = content;
        this.order = order;;
    }
    public String getShortcontent(){ return this.shortcontent; }
    public String getContent(){ return this.content; }
    public int getOrder() { return this.order; }
    public void setOrder(int i) { this.order = i;return; }
    public String getid() { return this.id; }
    public void addAnswer(AnswersE Answer1){
        System.out.println(Answer1);
        try {
            this.Answers.add(Answer1);
        }catch (Exception et){System.out.println(et);}
        return;
    }
    public List<AnswersE> getList()
    {
        return this.Answers;
    }


    @Override
    public String toString() {
        if(this.Answers.size() > 0)
        return String.format(
                "QuestionE[id=%s, shortcontent='%s', content='%s', order='%d', Ans = '%s']", id, shortcontent, content, order, Answers.get(0));
        else
            return String.format("QuestionE[id=%s, shortcontent='%s', content='%s', order='%d']", id, shortcontent, content, order);
    }
}

package org.example.sweater;
import java.util.List;

import org.example.sweater.entitys.QuestionE;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QuestionsRepository extends MongoRepository<QuestionE, String> {
    @Query("{\"shortcontent\" : { $regex: /?0/}}")
    public List<QuestionE> findByShortcontent(String shortcontent);
    @Query("{ 'id' : ?0 }")
    public List<QuestionE> findByIdd(String id);
    //public List<QuestionE> findByContent(String content);
    public List<QuestionE> findAll();
    //public List<QuestionE> findAllOrderByOrderAsc();
}

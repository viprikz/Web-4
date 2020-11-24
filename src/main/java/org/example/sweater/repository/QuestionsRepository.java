package org.example.sweater.repository;
import java.util.List;

import org.example.sweater.entitys.QuestionE;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QuestionsRepository extends MongoRepository<QuestionE, String> {
    @Query("{\"shortcontent\" : { $regex: /?0/}}")
    public List<QuestionE> findByShortcontent(String shortcontent);
    @Query("{ 'id' : ?0 }")
    public List<QuestionE> findByIdd(String id);
    //@Query(sort = "{{ age : -1 }, $maxScan: 2} ")
    /*@Query("{ id : -1 }")
    public List<QuestionE> findtop10();*/
    //public List<QuestionE> findByContent(String content);
    //@Query(sort = "{ 'id' : -1 }")
    //@Query(value = "db.questionE.find().limit(3)")
    public List<QuestionE> findAll();
    //public List<QuestionE> findAllOrderByOrderAsc();
}

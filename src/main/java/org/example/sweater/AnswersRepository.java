/*package org.example.sweater;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AnswersRepository extends MongoRepository<AnswersE, String> {
    @Query("{ 'fatherId' : ?0 }")
    public List<AnswersE> findByFatherId(String id);
    @Query("{ 'id' : ?0 }")
    public List<QuestionE> findByIdd(String id);
    public List<QuestionE> findByShortcontent(String content);
    //@Query(sort = "{ order : -1 }")
    //public List<QuestionE> findAllByContent();
    //public List<QuestionE> findAllOrderByOrderAsc();
}*/
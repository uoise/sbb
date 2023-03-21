package com.ll.sbb.question.repository;

import com.ll.sbb.question.model.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT 1", nativeQuery = true)
    void clearAutoIncrement();
    // @Modofying 쿼리가 결과집합을 리턴하지 않는다면 붙여야 함
}

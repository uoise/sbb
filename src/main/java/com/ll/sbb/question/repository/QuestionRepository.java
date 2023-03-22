package com.ll.sbb.question.repository;

import com.ll.sbb.question.model.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    @Query("select " +
            "distinct q " +
            "from Question q " +
            "left outer join SiteUser u1 on q.author=u1 " +
            "left outer join Answer a on a.question=q " +
            "left outer join SiteUser u2 on a.author=u2 " +
            "where " +
            "   q.subject like %:kw% " +
            "   or q.content like %:kw% " +
            "   or u1.username like %:kw% " +
            "   or a.content like %:kw% " +
            "   or u2.username like %:kw%"
    )
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT 1", nativeQuery = true)
    void clearAutoIncrement();
    // @Modofying 쿼리가 결과집합을 리턴하지 않는다면 붙여야 함
}

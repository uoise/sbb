package com.ll.sbb.question.repository;

import com.ll.sbb.question.model.Question;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


public interface QuestionCustomRepository {
    PageImpl<Question> findAllByKeyword(String kw, Pageable pageable);
}

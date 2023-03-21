package com.ll.sbb.answer.service;

import com.ll.sbb.answer.model.Answer;
import com.ll.sbb.answer.repository.AnswerRepository;
import com.ll.sbb.question.model.Question;
import com.ll.sbb.user.model.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Long create(Question question, String content, SiteUser author) {
        return answerRepository.save(Answer.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .question(question)
                .author(author)
                .build()
        ).getId();
    }
}

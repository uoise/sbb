package com.ll.sbb.answer.service;

import com.ll.sbb.DataNotFoundException;
import com.ll.sbb.answer.model.Answer;
import com.ll.sbb.answer.repository.AnswerRepository;
import com.ll.sbb.question.model.Question;
import com.ll.sbb.user.model.SiteUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional
    public Long create(Question question, String content, SiteUser author) {
        return answerRepository.save(Answer.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .question(question)
                .author(author)
                .build()
        ).getId();
    }

    @Transactional
    public Answer getAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    @Transactional
    public Long modify(Answer answer, String content) {
        return answerRepository.save(Answer.builder()
                .id(answer.getId())
                .author(answer.getAuthor())
                .question(answer.getQuestion())
                .createDate(answer.getCreateDate())
                .modifyDate(LocalDateTime.now())
                .content(content)
                .build()
        ).getId();
    }

    @Transactional
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    @Transactional
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }
}

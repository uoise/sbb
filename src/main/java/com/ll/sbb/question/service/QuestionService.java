package com.ll.sbb.question.service;

import com.ll.sbb.DataNotFoundException;
import com.ll.sbb.question.model.Question;
import com.ll.sbb.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional
    public List<Question> getList() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("Question not found");
        }
    }
}


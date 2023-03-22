package com.ll.sbb.question.service;

import com.ll.sbb.DataNotFoundException;
import com.ll.sbb.answer.model.Answer;
import com.ll.sbb.question.model.Question;
import com.ll.sbb.question.repository.QuestionRepository;
import com.ll.sbb.user.model.SiteUser;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"),
                        cb.like(q.get("content"), "%" + kw + "%"),
                        cb.like(u1.get("username"), "%" + kw + "%"),
                        cb.like(a.get("content"), "%" + kw + "%"),
                        cb.like(u2.get("username"), "%" + kw + "%"));
            }
        };
    }


    @Transactional
    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return questionRepository.findAllByKeyword(kw, pageable);
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

    @Transactional
    public Long create(String subject, String content, SiteUser siteUser) {
        return questionRepository.save(Question.builder()
                .subject(subject)
                .content(content)
                .createDate(LocalDateTime.now())
                .author(siteUser)
                .build()
        ).getId();
    }

    @Transactional
    public Page<Question> getList(int page) {
        // wtf
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return questionRepository.findAll(pageable);
    }

    @Transactional
    public Long modify(Question question, String subject, String content) {
        return questionRepository.save(Question.builder()
                .id(question.getId())
                .author(question.getAuthor())
                .createDate(question.getCreateDate())
                .modifyDate(LocalDateTime.now())
                .subject(subject)
                .content(content)
                .build()
        ).getId();
    }

    @Transactional
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Transactional
    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        questionRepository.save(question);
    }
}


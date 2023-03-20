package com.ll.sbb;

import com.ll.sbb.answer.model.Answer;
import com.ll.sbb.answer.repository.AnswerRepository;
import com.ll.sbb.question.model.Question;
import com.ll.sbb.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testSaveJpa() {
        questionRepository.save(
                Question.builder()
                        .subject("Sbb 가 무엇인가요?")
                        .content("SBB에 대해 알고싶습니다.")
                        .createDate(LocalDateTime.now())
                        .build()
        );
        questionRepository.save(
                Question.builder()
                        .subject("스프링 부트 모델이 무엇인가요?")
                        .content("ID는 자동으로 생성되나요? 대해 알고싶습니다.")
                        .createDate(LocalDateTime.now())
                        .build()
        );
    }

    @Transactional
    @Test
    void testFindJpa() {
        List<Question> all = questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("Sbb 가 무엇인가요?", q.getSubject());

        Optional<Question> oq = questionRepository.findById(1L);
        if (oq.isPresent()) {
            q = oq.get();
            assertEquals("Sbb 가 무엇인가요?", q.getSubject());
        }

        q = questionRepository.findBySubject("Sbb 가 무엇인가요?");
        assertEquals(1L, q.getId());

        q = questionRepository.findBySubjectAndContent("Sbb 가 무엇인가요?", "SBB에 대해 알고싶습니다.");
        assertEquals(1L, q.getId());

        List<Question> qList = questionRepository.findBySubjectLike("Sbb%");
        q = qList.get(0);
        assertEquals(q.getSubject(), "Sbb 가 무엇인가요?");

        oq = questionRepository.findById(1L);
        assertTrue(oq.isPresent());
        q = oq.get();
        q.setSubject("Sbb 가 무엇인가요?");
        questionRepository.save(q);


        oq = questionRepository.findById(2L);
        assertTrue(oq.isPresent());
        q = oq.get();
        answerRepository.save(Answer.builder().content("네 자동으로 생성됩니다.").question(q).createDate(LocalDateTime.now()).build());

        Optional<Answer> oa = answerRepository.findById(1L);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(1L, a.getId());

        oq = questionRepository.findById(2L);
        assertTrue(oq.isPresent());
        q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }
}

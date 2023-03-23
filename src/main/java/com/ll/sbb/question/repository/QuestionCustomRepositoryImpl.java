package com.ll.sbb.question.repository;

import com.ll.sbb.answer.model.QAnswer;
import com.ll.sbb.question.model.QQuestion;
import com.ll.sbb.question.model.Question;
import com.ll.sbb.user.model.QSiteUser;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    QQuestion question = QQuestion.question;
    QAnswer answer = QAnswer.answer;
    QSiteUser author1 = new QSiteUser("author1");
    QSiteUser author2 = new QSiteUser("author2");

    @Override
    public PageImpl<Question> findAllByKeyword(String kw, Pageable pageable) {
        QueryResults<Question> results = jpaQueryFactory.selectFrom(question)
                .distinct()
                .leftJoin(author1).on(question.author.id.eq(author1.id))
                .leftJoin(answer).on(question.answerList.contains(answer))
                .leftJoin(author2).on(answer.author.id.eq(author2.id))
                .where(
                        question.subject.contains(kw)
                                .or(question.content.contains(kw))
                                .or(question.author.username.contains(kw))
                                .or(answer.content.contains(kw))
                                .or(answer.author.username.contains(kw))
                ).fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}

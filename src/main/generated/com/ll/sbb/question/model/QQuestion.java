package com.ll.sbb.question.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = 722276823L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final ListPath<com.ll.sbb.answer.model.Answer, com.ll.sbb.answer.model.QAnswer> answerList = this.<com.ll.sbb.answer.model.Answer, com.ll.sbb.answer.model.QAnswer>createList("answerList", com.ll.sbb.answer.model.Answer.class, com.ll.sbb.answer.model.QAnswer.class, PathInits.DIRECT2);

    public final com.ll.sbb.user.model.QSiteUser author;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final SetPath<com.ll.sbb.user.model.SiteUser, com.ll.sbb.user.model.QSiteUser> voter = this.<com.ll.sbb.user.model.SiteUser, com.ll.sbb.user.model.QSiteUser>createSet("voter", com.ll.sbb.user.model.SiteUser.class, com.ll.sbb.user.model.QSiteUser.class, PathInits.DIRECT2);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.ll.sbb.user.model.QSiteUser(forProperty("author")) : null;
    }

}


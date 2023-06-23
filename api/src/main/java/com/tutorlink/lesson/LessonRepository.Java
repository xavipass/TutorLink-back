package com.tutorlink.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LessonRepository {

    private final EntityManager em;

    public LessonRepository(EntityManager em) { this.em = em; }

    public void save(Lesson lesson) {em.persist(lesson);}

    public Lesson findOne(Long id) {return em.find(Lesson.class, id);}

    public List<Lesson> findAll() {
        return em.createQuery("select l from Lesson l", Lesson.class)
                .getResultList();
    }

//    public List<Lesson> findAllByString(LessonSearch lessonSearch) {
//
//        //유저명 검색
//
//        //레슨명 검색
//
//        //레슨타입 검색
//
//        //레슨상태(OPEN/CLOSE/CANCEL) 검색
//
//    }


}

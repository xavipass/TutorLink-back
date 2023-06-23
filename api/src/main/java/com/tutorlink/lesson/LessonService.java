package com.tutorlink.lesson;

import com.tutorlink.user.User;
import com.tutorlink.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    /**
     * 레슨 생성
     */
    @Transactional
    public Long newLesson(Long user_id) {

        //엔티티 조회
        User user = userRepository.findOne(user_id);

        //주문 생성
        Lesson lesson = Lesson.createLesson(user);

        //주문 저장
        lessonRepository.save(lesson);

        return lesson.getLesson_id();
    }

    /**
     * 레슨 취소
     */
    @Transactional
    public void cancelLesson(Long lesson_Id) {
        //주문 엔티티 조회
        Lesson lesson = lessonRepository.findOne(lesson_Id);
        //주문 취소
        lesson.cancel();
    }


    /**
     * 레슨 전체 조회
     */
    public List<Lesson> findLessons() {
        return lessonRepository.findAll();
    }


    @Transactional
    public void update(long lesson_id, String title) {
        Lesson lesson = lessonRepository.findOne(lesson_id);
        lesson.setTitle(title);
    }

    /**
     * 레슨 검색
     */
//    public List<Lesson> findLessons(LessonSearch lessonSearch) {
//        return LessonRepository.findAllByString(lessonSearch);
//    }


}




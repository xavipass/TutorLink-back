package com.tutorlink.lesson;

import com.tutorlink.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Lesson extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lesson_id; //과외방번호

    @Column(updatable = false, nullable = false, unique = true)
    private String title; //방제목

    @Column(nullable = false)
    private String password; //비밀번호

    @Enumerated(EnumType.STRING)
    private LessonType lesson_type; //과외유형

    @Enumerated(EnumType.STRING)
    private LessonStatus status; //과외상태

    @Column(insertable = false, columnDefinition = "integer default 0")
    private Long cnt; //조회수

    @Column(columnDefinition = "integer default 0")
    private Long like_count; //좋아요수

    private String image; //썸네일

    @CreatedDate
    public LocalDateTime createdDate; //생성일시

    @LastModifiedDate
    private LocalDateTime modifiedDate; //수정일시

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //작성자아이디




    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getLessons().add(this);
    }

    //==생성 메서드==//
    public static Lesson createLesson(User user) {
        Lesson lesson = new Lesson();
        lesson.setUser(user);
//        for (LessonItem lessonItem : lessonItems) {
//            lesson.addlessonItem(lessonItem);
//        }
        lesson.setStatus(LessonStatus.OPEN);
        lesson.setCreatedDate(LocalDateTime.now());
        return lesson;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {
        if (this.getStatus() == LessonStatus.CLOSE) {
            throw new IllegalStateException("이미 취소한 레슨입니다.");
        }
        this.setStatus(LessonStatus.CANCEL);
    }









}

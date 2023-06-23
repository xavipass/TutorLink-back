package com.tutorlink.api.lesson;

import com.tutorlink.lesson.Lesson;
import com.tutorlink.lesson.LessonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LessonApiController {

    private final LessonService lessonService;
//    private final UserService userService;

    @ApiOperation(value = "레슨방 목록 조회", notes = "전체 레슨방 목록 조회")
    @GetMapping("/api/v2/lessons")
    public Result lessonV2() {
        List<Lesson> findLessons = lessonService.findLessons();
        List<LessonDto> collect = findLessons.stream()
                .map(l -> new LessonDto(l.getTitle()))
                .collect(Collectors.toList());

        return new Result(collect.size(),collect);

    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class LessonDto {
        @Schema(description="레슨방 제목")
        private String title;
    }


    @ApiOperation(value="레슨방 생성", notes="새 레슨방 생성")
    @PostMapping("/api/v2/lessons")
    public CreateLessonResponse saveLessonV2(@RequestBody @Valid CreateLessonRequest request) {

        Lesson lesson = new Lesson();
        lesson.setTitle(request.getTitle());

        Long lesson_id = lessonService.newLesson(user);
        return new CreateLessonResponse(lesson_id);
    }

    @Data
    static class CreateLessonRequest {
        @NotEmpty
        private String title;
    }

    @Data
    static class CreateLessonResponse {
        private Long lesson_id;

        public CreateLessonResponse(Long lesson_id) {
            this.lesson_id = lesson_id;
        }
    }

    @ApiOperation(value = "레슨방 수정", notes = "레슨방 수정")
    @ApiImplicitParam(name="lesson_id", value="레슨방 번호")
    @PutMapping("/api/v2/lessons/{lesson_id}")
    public UpdateLessonResponse updateMemberV2 (
            @PathVariable("lesson_id") Long lesson_id,
            @RequestBody @Valid UpdateLessonRequest request) {

        lessonService.update(lesson_id, request.getTitle());
        Lesson findLesson = lessonService.findLessons(lesson_id);
        return new UpdateLessonResponse(findLessons.getLesson_id(), findLessons.getTitle());

    }

    @Data
    static class UpdateLessonRequest{
        private String title;
    }

    @Data
    @AllArgsConstructor
    static class  UpdateLessonResponse{
        private Long lesson_id;
        private String title;
    }



}

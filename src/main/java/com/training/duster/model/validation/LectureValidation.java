package com.training.duster.model.validation;
import com.training.duster.model.entity.Lecture;
import java.time.LocalDateTime;
/**
 * The {@code LectureValidation} is a interface which provide method to validate {@link Lecture}
 */
public interface LectureValidation {
    boolean startTimeValid(LocalDateTime lectureTime, LocalDateTime conferenceTime);

    boolean titleValid(String title);

    boolean descriptionValid(String description);

    boolean titleEnValid(String title);

    boolean descriptionEnValid(String description);
}

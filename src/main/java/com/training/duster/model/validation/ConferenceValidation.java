package com.training.duster.model.validation;
import com.training.duster.model.entity.Conference;

import java.time.LocalDateTime;
/**
 * The {@code ConferenceValidation} is a interface which provide method to validate {@link Conference}
 */
public interface ConferenceValidation {

    boolean titleValid(String title);

    boolean titleEnValid(String title);

    boolean descriptionValid(String description);

    boolean descriptionEnValid(String description);

    boolean dateTimeValid(LocalDateTime dateTime);

    boolean placeValid(String place);

    boolean placeEnValid(String place);

    boolean lecturesCapacityValid(int lecturesCapacity);

    boolean placeCapacity(int placeCapacity);
}

package com.training.duster.model.validation;
import com.training.duster.model.entity.Speaker;
/**
 * The {@code SpeakerValidation} is a interface which provide method to validate {@link Speaker}
 */
public interface SpeakerValidation {
    boolean ratingValid(double rating);
}



package com.training.duster.model.dao;

import com.training.duster.model.entity.Speaker;

import java.math.BigDecimal;
/**
 * The {@code SpeakerDao} implements {@link GenericDao}
 * interface for ORM database entity {@link Speaker}
 */
public interface SpeakerDao extends GenericDao<Speaker> {
    /**
     * Method to change {@link Speaker}'s rating
     *
     * @param newRating is a new rating value
     * @param speakerId is a {@link Speaker}'s {@code id} to change rating
     * @return returns {@code true} if {@code rating} was changed succeed
     *         or else {@code false}
     */
    boolean changeRating(double newRating, long speakerId);
    /**
     * Method to change {@link Speaker}'s bonus depends on his rating
     *
     * @param speakerId is a {@link Speaker}'s {@code id} to change bonus
     * @return returns {@code true} if {@code rating} was changed succeed
     *         or else {@code false}
     */
    boolean transferBonus(BigDecimal bonus, long speakerId);
}

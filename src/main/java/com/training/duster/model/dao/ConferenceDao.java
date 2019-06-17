package com.training.duster.model.dao;

import com.training.duster.model.entity.Conference;
import com.training.duster.model.entity.User;

import java.util.List;

/**
 * The {@code ConferenceDao} implements {@link GenericDao}
 * interface for ORM database entity {@link Conference}
 */
public interface ConferenceDao extends GenericDao<Conference> {
    /**
     * Method to get all {@link Conference} with subscribed {@link User}s
     * @return return {@link List} of all {@link Conference}
     */
    List<Conference> findAllSubscribed();
}

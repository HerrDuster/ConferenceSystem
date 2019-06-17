package com.training.duster.model.service;

import com.training.duster.model.dao.UserDao;
import com.training.duster.model.entity.Conference;
import com.training.duster.model.entity.User;

import java.sql.SQLException;
import java.util.Set;
/**
 * The {@code UserService} service is a specified API for working with the {@link UserDao}
 */
public interface UserService {
    /**
     * Method to get all {@link User}'s logins
     *
     * @return returns list of strings if search was succeed
     *         or else {@code null}
     */
    Set<String> getAllUsersLogins();
    /**
     * Method to create {@link User}
     *
     * @param user this {@code user} will be created
     * @return returns {@code true} if {@link User} was created succeed
     *         or else {@code false}
     */
    boolean create(User user) throws SQLException;
    /**
     * Method to subscribe {@link User} on {@link Conference}
     *
     * @param userId this {@code user} will be subscribed
     * @param conferenceId on this {@code conference} will be subscribed
     * @return returns {@code true} if {@link User} was subscribed succeed
     *         or else {@code false}
     */
    boolean subscribeOnConference(long userId, long conferenceId);
}

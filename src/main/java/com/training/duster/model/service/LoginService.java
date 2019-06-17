package com.training.duster.model.service;

import com.training.duster.model.entity.User;
/**
 * The {@code LoginService} service is a specified API for {@link User} authentication
 */
public interface LoginService {
    /**
     * Method to login {@link User}
     *
     * @param login {@link User} with this {@code login} will be logged in
     * @param password {@link User} with this {@code password} will be logged in
     * @return returns {@code User} if {@link User} was logged in succeed
     *         or else {@code null}
     */
    User login(String login, String password);
}

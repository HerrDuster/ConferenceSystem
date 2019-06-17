package com.training.duster.controller.command.impl.redirect;

import com.training.duster.controller.command.Command;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code RedirectRegistrationCommand} class implements {@link Command}
 * and is used for redirect to the registration page
 */
public class RedirectRegistrationCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PathManager.getProperty("path.page.registration");
    }
}



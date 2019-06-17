package com.training.duster.controller.command.impl;

import com.training.duster.controller.command.Command;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code ChangeLanguageCommand} class implements {@link Command}
 * and is used for changing the language and redirect to previous command
 */
public class ChangeLanguageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.class);
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(AttributesManager.getProperty("language"), request.getParameter(AttributesManager.getProperty("language")));
        LOGGER.debug("Set a locale into session and redirect to: " + PathManager.getProperty("redirect.page.main"));
        return request.getHeader("referer");
    }
}



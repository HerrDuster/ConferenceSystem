package com.training.duster.controller.command.impl.redirect;

import com.training.duster.controller.command.Command;
import com.training.duster.model.entity.Conference;
import com.training.duster.model.service.ConferenceService;
import com.training.duster.model.service.impl.DefaultConferenceService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code RedirectChangeConferenceCommand} class implements {@link Command}
 * and is used for redirect to the conference page and fill it with data
 */
public class RedirectChangeConferenceCommand implements Command {
    private static ConferenceService conferenceService;

    public RedirectChangeConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        Conference conference = conferenceService.getById(conferenceId);
        request.setAttribute(AttributesManager.getProperty("conference"), conference);
        return PathManager.getProperty("path.page.change.conference");
    }
}



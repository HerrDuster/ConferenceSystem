package com.training.duster.controller.command.impl;

import com.training.duster.controller.command.Command;
import com.training.duster.controller.command.GenerateUser;
import com.training.duster.model.entity.Conference;
import com.training.duster.model.entity.User;
import com.training.duster.model.service.ConferenceService;
import com.training.duster.model.service.impl.DefaultConferenceService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * The {@code MainCommand} class implements {@link Command}
 * and is used for filling the data on main page
 */
public class MainCommand implements Command, GenerateUser {
    private static ConferenceService conferenceService;

    public MainCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = getUserFromSession(request);
        if (user != null) {
            long currentTIme = System.currentTimeMillis();
            List<Conference> ongoing = conferenceService.getOngoingConferences(currentTIme);
            List<Conference> last = conferenceService.getLastConferences(currentTIme);

            request.setAttribute(AttributesManager.getProperty("ongoing.attribute"), ongoing);
            request.setAttribute(AttributesManager.getProperty("last.attribute"), last);
        }
        return PathManager.getProperty("path.page.main");
    }
}



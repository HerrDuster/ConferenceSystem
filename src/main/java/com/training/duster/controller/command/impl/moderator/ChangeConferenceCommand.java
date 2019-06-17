package com.training.duster.controller.command.impl.moderator;

import com.training.duster.controller.command.Command;
import com.training.duster.controller.command.GenerateConference;
import com.training.duster.model.dto.InvalidData;
import com.training.duster.model.entity.Conference;
import com.training.duster.model.service.ConferenceService;
import com.training.duster.model.service.impl.DefaultConferenceService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code ChangeConferenceCommand} class implements {@link Command}, {@link GenerateConference}
 * and is used for editing the {@link Conference} by Moderator
 */
public class ChangeConferenceCommand implements Command, GenerateConference {
    private static ConferenceService conferenceService;

    public ChangeConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Conference conference = getConferenceFromRequest(request);
        conference.setId(Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString()));

        InvalidData invalidData = inputChecked(conference);

        if (invalidData == null) {
            LOGGER.info("Validation was succeed");
            tryToUpdateConference(conference, request);
        } else {
            LOGGER.debug("Invalid input");
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
            request.setAttribute(AttributesManager.getProperty("invalid.data"), invalidData);
        }
        return PathManager.getProperty("path.page.change.conference");
    }
    private void tryToUpdateConference(Conference conference, HttpServletRequest request){
        if (conferenceService.update(conference)) {
            LOGGER.info("Conference was updated");
            request.setAttribute(AttributesManager.getProperty("success.update"), true);
            conference = conferenceService.getById(conference.getId());
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
        } else {
            LOGGER.debug("Conference wasn't updated");
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
            request.setAttribute(AttributesManager.getProperty("not.update.conf"), true);
        }
    }
}



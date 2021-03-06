package com.training.duster.controller.command.impl.moderator;

import com.training.duster.controller.command.Command;
import com.training.duster.model.entity.Conference;
import com.training.duster.model.service.ConferenceService;
import com.training.duster.model.service.impl.DefaultConferenceService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code DeleteConferenceCommand} class implements {@link Command}
 * and is used for deleting the {@link Conference} from the database by Moderator
 */
public class DeleteConferenceCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteConferenceCommand.class);
    private static ConferenceService conferenceService;

    public DeleteConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getParameter(AttributesManager.getProperty("conference.id")));
        if (conferenceService.delete(conferenceId)) {
            LOGGER.debug("Delete was succeed");
            return PathManager.getProperty("redirect.page.main");
        }
        LOGGER.debug("Delete wasn't succeed");
        Conference conference = conferenceService.getById(conferenceId);
        request.setAttribute(AttributesManager.getProperty("conference"), conference);
        request.setAttribute(AttributesManager.getProperty("conf.not.delete"), true);
        return PathManager.getProperty("path.page.change.conference");
    }
}



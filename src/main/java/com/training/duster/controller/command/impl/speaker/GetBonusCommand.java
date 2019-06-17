package com.training.duster.controller.command.impl.speaker;

import com.training.duster.controller.command.Command;
import com.training.duster.model.entity.User;
import com.training.duster.model.entity.Speaker;
import com.training.duster.model.service.SpeakerService;
import com.training.duster.model.service.impl.DefaultSpeakerService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code GetBonusCommand} class implements {@link Command}
 * and is used for changing the {@link Speaker}'s bonus to zero by Speaker and transfer it
 */
public class GetBonusCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetBonusCommand.class);
    private static SpeakerService speakerService;

    public GetBonusCommand() {
        speakerService = DefaultSpeakerService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesManager.getProperty("user"));

        if(speakerService.getBonus(user.getId())){
            LOGGER.debug("Bonus was zeroed");
            request.setAttribute(AttributesManager.getProperty("speaker"),  speakerService.getById(user.getId()));
            request.setAttribute(AttributesManager.getProperty("bonus.transfered"), true);
        }else {
            LOGGER.debug("Bonus wasn't updated");
            request.setAttribute(AttributesManager.getProperty("bonus.not.transfered"), true);
        }
        return PathManager.getProperty("path.page.profile");
    }
}



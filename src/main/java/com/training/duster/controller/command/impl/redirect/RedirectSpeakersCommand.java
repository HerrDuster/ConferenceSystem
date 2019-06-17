package com.training.duster.controller.command.impl.redirect;

import com.training.duster.controller.command.Command;
import com.training.duster.model.entity.Speaker;
import com.training.duster.model.service.SpeakerService;
import com.training.duster.model.service.impl.DefaultSpeakerService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * The {@code RedirectSpeakersCommand} class implements {@link Command}
 * and is used for redirect to the speakers list page and  fill it with data
 */
public class RedirectSpeakersCommand implements Command {
    private static SpeakerService speakerService;

    public RedirectSpeakersCommand() {
        speakerService = DefaultSpeakerService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Speaker> speakers = speakerService.getAll();
        request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
        return PathManager.getProperty("path.page.speakers");
    }
}



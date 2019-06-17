package com.training.duster.controller.command.impl.redirect;

import com.training.duster.controller.command.Command;
import com.training.duster.model.entity.Lecture;
import com.training.duster.model.entity.Speaker;
import com.training.duster.model.service.LectureService;
import com.training.duster.model.service.SpeakerService;
import com.training.duster.model.service.impl.DefaultLectureService;
import com.training.duster.model.service.impl.DefaultSpeakerService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * The {@code RedirectChangeLectureCommand} class implements {@link Command}
 * and is used for redirect to the lecture page and fill it with data
 */
public class RedirectChangeLectureCommand implements Command {
    private static LectureService lectureService;
    private static SpeakerService speakerService;

    public RedirectChangeLectureCommand(){
        lectureService = DefaultLectureService.getInstance();
        speakerService = DefaultSpeakerService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
        Lecture lecture = lectureService.getById(lectureId);
        List<Speaker> speakers = speakerService.getAll();
        request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
        request.setAttribute(AttributesManager.getProperty("speakers"), speakers);
        return PathManager.getProperty("path.page.change.lecture");
    }
}



package com.training.duster.controller.command.impl.redirect;

import com.training.duster.controller.command.Command;
import com.training.duster.model.dto.Report;
import com.training.duster.model.entity.Conference;
import com.training.duster.model.service.ConferenceService;
import com.training.duster.model.service.impl.DefaultConferenceService;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code RedirectCreateReportCommand} class implements {@link Command}
 * and is used for redirect to the create report page
 * and fill it with {@link Report} data
 */
public class RedirectCreateReportCommand implements Command {
    private static ConferenceService conferenceService;

    public RedirectCreateReportCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        Conference currentConference = conferenceService.getById(conferenceId);

        Report report = new Report();
        report.setConferenceTitle(currentConference.getTitle());
        report.setConferenceTitleEn(currentConference.getTitleEn());
        report.setDateTime(currentConference.getDateTime());
        report.setProvidedLecturesAmount(currentConference.getConferenceLectures().size());
        report.setRegisteredParticipantsAmount(currentConference.getRegisteredParticipants().size());

        request.setAttribute(AttributesManager.getProperty("report"), report);
        return PathManager.getProperty("path.page.create.report");
    }


}



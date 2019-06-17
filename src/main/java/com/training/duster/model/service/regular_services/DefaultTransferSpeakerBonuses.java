package com.training.duster.model.service.regular_services;

import com.training.duster.model.entity.Speaker;
import com.training.duster.model.service.SpeakerService;
import com.training.duster.model.service.impl.DefaultSpeakerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import  com.training.duster.model.dao.SpeakerDao;

import java.util.List;
/**
 * The {@code DefaultTransferSpeakerBonuses} service is a class to transfer bonuses using {@link SpeakerDao}
 */
public class DefaultTransferSpeakerBonuses implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(DefaultTransferSpeakerBonuses.class);
    private static SpeakerService speakerService;

    public DefaultTransferSpeakerBonuses() {
        speakerService = DefaultSpeakerService.getInstance();
    }

    @Override
    public void run() {
        List<Speaker> speakers = speakerService.getAll();
        LOGGER.debug("Try to transfer bonus for each speaker");
        speakers.forEach(speaker -> speakerService.transferBonus(speaker.getId()));
    }
}



package com.training.duster.model.service;

import com.training.duster.model.dto.Report;
/**
 * The {@code ReportService} service is a specified interface for working with the {@link Report}
 */
public interface ReportService {
    /**
     * Method to create {@link Report}
     *
     * @param report this {@code report} will be created
     * @return returns {@code true} if {@link Report} was created succeed
     *         or else {@code false}
     */
    boolean create(Report report);
}

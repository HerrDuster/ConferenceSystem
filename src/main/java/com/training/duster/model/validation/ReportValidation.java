package com.training.duster.model.validation;
import com.training.duster.model.dto.Report;
/**
 * The {@code ReportValidation} is a interface which provide method to validate {@link Report}
 */
public interface ReportValidation {
    boolean actualParticipantsAmountValid(int actual, int registered);
}

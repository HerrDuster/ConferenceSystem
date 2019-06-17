package com.training.duster.model.validation;
import com.training.duster.model.entity.User;
/**
 * The {@code UserValidation} is a interface which provide method to validate {@link User}
 */
public interface UserValidation {
    boolean loginValid(String login);

    boolean emailValid(String email);

    boolean passwordValid(String password);

    boolean firstNameValid(String firstName);

    boolean lastNameValid(String lastName);

    boolean firstNameEnValid(String firstNameEn);

    boolean lastNameEnValid(String lastNameEn);

}

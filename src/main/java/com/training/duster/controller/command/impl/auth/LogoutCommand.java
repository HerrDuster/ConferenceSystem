package com.training.duster.controller.command.impl.auth;

import com.training.duster.controller.command.Command;
import com.training.duster.controller.command.GenerateUser;
import com.training.duster.model.entity.User;
import com.training.duster.util.AttributesManager;
import com.training.duster.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * The {@code LogoutCommand} class implements {@link Command}, {@link GenerateUser}
 * and is used for removing user from session and context and for session invalidation
 */
public class LogoutCommand implements Command, GenerateUser {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = getUserFromSession(request);

        HttpSession session = request.getSession();
        session.removeAttribute(AttributesManager.getProperty("user"));
        session.getServletContext().removeAttribute(currentUser.getLogin());
        session.invalidate();
        return PathManager.getProperty("redirect.page.main");
    }
}



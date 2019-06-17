package com.training.duster.model.service.impl;

import com.training.duster.model.dao.DaoFactory;
import com.training.duster.model.dao.UserDao;
import com.training.duster.model.entity.User;
import com.training.duster.model.service.LoginService;
import com.training.duster.util.SecurityHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * {@inheritDoc}
 */
public class DefaultLoginService implements LoginService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultLoginService.class);

    private static volatile LoginService loginService;
    private static UserDao userDao;

    private DefaultLoginService() {
        userDao = DaoFactory.getInstance().getUserDao();
    }
    /**
     * Always return same {@link DefaultLoginService} instance
     *
     * @return always return same {@link DefaultLoginService} instance
     */
    public static LoginService getInstance(){
        LoginService localInstance = loginService;
        if(localInstance == null) {
            synchronized (DefaultLoginService.class) {
                localInstance = loginService;
                if(localInstance == null) {
                    loginService = new DefaultLoginService();
                    LOGGER.debug("Create first DefaultLoginService instance");
                }
            }
        }
        LOGGER.debug("Return DefaultLoginService instance");
        return loginService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User login(String login, String password) {
        User user = userDao.findByLogin(login);
        if(user != null && new SecurityHash().checkPassword(password, user.getPassword()))
            return user;
        return null;
    }
}



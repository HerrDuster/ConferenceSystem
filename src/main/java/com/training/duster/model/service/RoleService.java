package com.training.duster.model.service;

import com.training.duster.model.entity.Role;
import com.training.duster.model.dao.RoleDao;
import java.util.List;
/**
 * The {@code RoleService} service is a specified API for working with the {@link RoleDao}
 */
public interface RoleService {
    /**
     * Method to get all {@link Role}s
     *
     * @return return {@link List} of all {@link Role}s
     */
    List<Role> getAll();
}

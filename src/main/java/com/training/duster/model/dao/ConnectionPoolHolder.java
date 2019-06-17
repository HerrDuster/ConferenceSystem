package com.training.duster.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * The {@code ConnectionPoolHolder} is an interface to get a connection to a database
 * from connection pool
 */
public interface ConnectionPoolHolder {
    static Connection getConnection() throws SQLException{
    return null;
    }
}

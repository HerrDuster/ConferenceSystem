package com.training.duster.model.dao.impl;

import com.training.duster.model.dao.ConferenceDao;
import com.training.duster.model.dao.mapper.impl.ConferenceMapper;
import com.training.duster.model.entity.Conference;
import com.training.duster.util.SQLManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code MySQLConferenceDao} class implements {@link ConferenceDao}
 * and specified for MySQL DB
 */
public class MySQLConferenceDao implements ConferenceDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLConferenceDao.class);

    private Connection connection;
    private ConferenceMapper conferenceMapper;

    public MySQLConferenceDao(Connection connection) {
        LOGGER.debug("MySQLConferenceDao constructor");
        this.connection = connection;
        conferenceMapper = new ConferenceMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(Conference entity) {
        boolean resultFlag = false;
        LOGGER.debug("Try to insert into conference table");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("insert.conference"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Insert was successful ");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:" + e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Conference findById(long id) {
        Conference result = null;
        LOGGER.debug("Try to find conference by id");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.conference.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            result = conferenceMapper.parseFromResultSet(rs, false);
            LOGGER.debug("Select was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Conference> findAll() {
        List<Conference> resultList = new ArrayList<>();
        Map<Long, Conference> conferences = new HashMap<>();
        LOGGER.debug("Try to find all conferences");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.conferences"));
            while (rs.next()) {
                Conference conference = conferenceMapper.parseFromResultSet(rs, true);
                conference = conferenceMapper.makeUnique(conferences, conference);
                if (!resultList.contains(conference)) {
                    resultList.add(conference);
                }
            }
            LOGGER.debug("Found all conferences successful ");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Conference entity) {
        boolean resultFlag = false;
        LOGGER.debug("Try to update conference");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.conference"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            stm.setLong(10, entity.getId());
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Successful update");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(long id) {
        boolean resultFlag = false;
        LOGGER.debug("Try to delete from conference");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("delete.conference"))) {
            stm.setLong(1, id);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Delete was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        LOGGER.debug("Try to close connection");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
            throw new RuntimeException(e);
        }
    }

    private void setGeneralParamsToPreparedStatement(PreparedStatement stm, Conference entity) throws SQLException {
        LOGGER.debug("Set params to prepare statement");
        stm.setString(1, entity.getTitle());
        stm.setString(2, entity.getTitleEn());
        stm.setString(3, entity.getDescription());
        stm.setString(4, entity.getDescriptionEn());
        stm.setTimestamp(5, Timestamp.valueOf(entity.getDateTime()));
        stm.setString(6, entity.getPlace());
        stm.setString(7, entity.getPlaceEn());
        stm.setInt(8, entity.getLecturesCapacity());
        stm.setInt(9, entity.getPlaceCapacity());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Conference> findAllSubscribed() {
        List<Conference> resultList = new ArrayList<>();
        LOGGER.debug("Try to find all conferences with subscribed users");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.subscribed.conferences"));
            resultList = conferenceMapper.parseFromResultSetSubscribed(rs);
            LOGGER.debug("Found all subscribed conferences successful ");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultList;
    }
}



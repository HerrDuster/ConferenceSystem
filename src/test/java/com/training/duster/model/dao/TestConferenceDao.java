package com.training.duster.model.dao;

import com.training.duster.model.dao.data.ConferenceBuilder;
import com.training.duster.model.dao.impl.MySQLConferenceDao;
import com.training.duster.model.entity.Conference;
import com.training.duster.util.TestConnectionPoolHolder;
import com.training.duster.util.TestDataBaseManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

class TestConferenceDao {
    private static ConferenceDao conferenceDao;

    @BeforeAll
    static void setUpClass() {
        TestDataBaseManager.setUpTestDDL();
        TestDataBaseManager.setUpTestDML();
        try {
            conferenceDao = new MySQLConferenceDao(new TestConnectionPoolHolder().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createTest() {
        Conference conference = ConferenceBuilder.getBuilder().constructConference(2L).build();
        assertTrue(conferenceDao.create(conference));
        assertEquals(conference, conferenceDao.findById(conference.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    void updateTest() {
        Conference conference = ConferenceBuilder.getBuilder().constructConference(1L).build();
        conference.setTitle("update");
        assertTrue(conferenceDao.update(conference));
        assertEquals(conference, conferenceDao.findById(conference.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    void deleteTest() {
        Conference conference = ConferenceBuilder.getBuilder().constructConference(1L).build();
        assertTrue(conferenceDao.delete(conference.getId()));
        assertNull(conferenceDao.findById(conference.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    void findAllTest() {
        List<Conference> expectedConferences = new ArrayList<>();
        expectedConferences.add(ConferenceBuilder.getBuilder().constructConference(1L).build());
        List<Conference> actualConferences = conferenceDao.findAll();
        assertEquals(expectedConferences, actualConferences);
    }

    @Test
    void findByIdTest() {
        Conference expectedConference = ConferenceBuilder.getBuilder().constructConference(1L).build();
        Conference actualConference = conferenceDao.findById(expectedConference.getId());
        assertEquals(expectedConference, actualConference);
    }
}



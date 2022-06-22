package com.skilldistillery.tooldragon.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParticipantTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Participant participant;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("ToolDragonJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		ParticipantId pid = new ParticipantId();
		pid.setProjectId(1);
		pid.setUserId(1);
		participant = em.find(Participant.class, pid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		participant = null;
	}

	@Test
	void test_Participant_entity_mapping() {
		assertNotNull(participant);
		assertNotNull(participant.getDateCreated());
	}

}

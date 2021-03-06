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

class VoteTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Vote vote;

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
		vote = em.find(Vote.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		vote = null;
	}

	@Test
	void test_Vote_entity_mapping() {
		assertNotNull(vote);
		assertEquals("upvote", vote.getName());
	}

}

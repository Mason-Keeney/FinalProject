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

class ProjectCommentVoteTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ProjectCommentVote projectCommentVote;

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
		ProjectCommentVoteId pid = new ProjectCommentVoteId();
		pid.setProjectCommentId(1);
		pid.setUserId(1);
		projectCommentVote = em.find(ProjectCommentVote.class, pid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		projectCommentVote = null;
	}

	@Test
	void test_ProjectCommentVote_entity_mapping() {
		assertNotNull(projectCommentVote);
		assertNotNull(projectCommentVote.getVoteDate());
	}

}

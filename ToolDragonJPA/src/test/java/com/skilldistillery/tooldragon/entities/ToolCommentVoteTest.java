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

class ToolCommentVoteTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ToolCommentVote toolCommentVote;

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
		ToolCommentVoteId pid = new ToolCommentVoteId();
		pid.setToolCommentId(1);
		pid.setUserId(1);
		toolCommentVote = em.find(ToolCommentVote.class, pid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		toolCommentVote = null;
	}

	@Test
	void test_ToolCommentVote_entity_mapping() {
		assertNotNull(toolCommentVote);
		assertNotNull(toolCommentVote.getVoteDate());
	}
	
	@Test
	void test_ToolCommentVote_User_mapping() {
		assertNotNull(toolCommentVote);
		assertNotNull(toolCommentVote.getUser());
	}
	
	@Test
	void test_ToolCommentVote_ToolComment_mapping() {
		assertNotNull(toolCommentVote);
		assertNotNull(toolCommentVote.getToolComment());
	}
	
	@Test
	void test_ToolCommentVote_Vote_mapping() {
		assertNotNull(toolCommentVote);
		assertNotNull(toolCommentVote.getVote());
	}

}

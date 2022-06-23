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

class ToolCommentTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ToolComment toolComment;

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
		toolComment = em.find(ToolComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		toolComment = null;
	}

	@Test
	void test_ToolComment_entity_mapping() {
		assertNotNull(toolComment);
		assertEquals("hello", toolComment.getCommentBody());
	}
	
	@Test
	void test_ToolComment_User_mapping() {
		assertNotNull(toolComment);
		assertNotNull(toolComment.getUser());
	}
	
	@Test
	void test_ToolComment_Tool_mapping() {
		assertNotNull(toolComment);
		assertNotNull(toolComment.getTool());
	}
	
	@Test
	void test_ToolComment_Vote_mapping() {
		assertNotNull(toolComment);
		assertNotNull(toolComment.getVotes());
		assertTrue(toolComment.getVotes().size() > 0);
	}

}

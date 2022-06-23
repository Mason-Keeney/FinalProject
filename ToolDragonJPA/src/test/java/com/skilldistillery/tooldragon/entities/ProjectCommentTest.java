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

class ProjectCommentTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ProjectComment projectComment;

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
		projectComment = em.find(ProjectComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		projectComment = null;
	}

	@Test
	void test_ProjectComment_entity_mapping() {
		assertNotNull(projectComment);
		assertEquals("project comment", projectComment.getCommentBody());
	}

	@Test
	void test_ProjectComment_Project_mapping() {
		assertNotNull(projectComment);
		assertNotNull(projectComment.getProject());
	}
	
	@Test
	void test_ProjectComment_User_mapping() {
		assertNotNull(projectComment);
		assertNotNull(projectComment.getUser());
	}
	
	@Test
	void test_ProjectComment_votes_mapping() {
		assertNotNull(projectComment);
		assertNotNull(projectComment.getVotes());
		assertTrue(projectComment.getVotes().size() > 0);
	}




}

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

class ToolTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Tool tool;

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
		tool = em.find(Tool.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		tool = null;
	}

	@Test
	void test_Tool_entity_mapping() {
		assertNotNull(tool);
		assertEquals("hammer", tool.getName());
	}
	
	@Test
	void test_Tool_owner_mapping() {
		assertNotNull(tool);
		assertNotNull(tool.getOwner());
	}
	
	@Test
	void test_Tool_comments_mapping() {
		assertNotNull(tool);
		assertNotNull(tool.getComments());
		assertTrue(tool.getComments().size() > 0);
	}
	
	@Test
	void test_Tool_condition_mapping() {
		assertNotNull(tool);
		assertNotNull(tool.getCondition());
	}
	
	@Test
	void test_Tool_categories_mapping() {
		assertNotNull(tool);
		assertNotNull(tool.getCategories());
		assertTrue(tool.getCategories().size() > 0);
	}
	
	@Test
	void test_Tool_status_mapping() {
		assertNotNull(tool);
		assertNotNull(tool.getStatus());
	}
	
	@Test
	void test_Tool_projectsUsedIn_mapping() {
		assertNotNull(tool);
		assertNotNull(tool.getProjectsUsedIn());
		assertTrue(tool.getProjectsUsedIn().size() > 0);
	}






}

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

}

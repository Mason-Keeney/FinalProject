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

class ToolConditionTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ToolCondition toolCondition;

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
		toolCondition = em.find(ToolCondition.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		toolCondition = null;
	}

	@Test
	void test_ToolCondition_entity_mapping() {
		assertNotNull(toolCondition);
		assertEquals("new", toolCondition.getName());
	}

}

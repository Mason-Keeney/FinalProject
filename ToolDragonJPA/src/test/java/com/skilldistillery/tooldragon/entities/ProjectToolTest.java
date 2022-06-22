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

class ProjectToolTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ProjectTool projectTool;

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
		ProjectToolId pid = new ProjectToolId();
		pid.setProjectId(1);
		pid.setToolId(1);
		projectTool = em.find(ProjectTool.class, pid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		projectTool = null;
	}

	@Test
	void test_ProjectTool_entity_mapping() {
		assertNotNull(projectTool);
		assertNotNull(projectTool.getDateCreated());
	}

}

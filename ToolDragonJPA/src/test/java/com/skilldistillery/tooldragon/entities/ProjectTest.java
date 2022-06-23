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

class ProjectTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Project project;

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
		project = em.find(Project.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		project = null;
	}

	@Test
	void test_Project_entity_mapping() {
		assertNotNull(project);
		assertEquals("landscaping my yard", project.getDescription().toLowerCase());
	}
	
	@Test
	void test_Project_address_mapping() {
		assertNotNull(project);
		assertNotNull(project.getAddress());
	}
	
	@Test
	void test_Project_Owner_mapping() {
		assertNotNull(project);
		assertNotNull(project.getOwner());
	}
	
	@Test
	void test_Project_Participants_mapping() {
		assertNotNull(project);
		assertNotNull(project.getParticipants());
		assertTrue(project.getParticipants().size() > 0);
	}
	
	@Test
	void test_Project_ProjectTools_mapping() {
		assertNotNull(project);
		assertNotNull(project.getProjectTools());
		assertTrue(project.getProjectTools().size() > 0);
	}
	
	@Test
	void test_Project_Categories_mapping() {
		assertNotNull(project);
		assertNotNull(project.getCategories());
		assertTrue(project.getCategories().size() > 0);
	}
	
	@Test
	void test_Project_Comments_mapping() {
		assertNotNull(project);
		assertNotNull(project.getComments());
		assertTrue(project.getComments().size() > 0);
	}
	
	@Test
	void test_Project_ProjectMaterials_mapping() {
		assertNotNull(project);
		assertNotNull(project.getProjectMaterials());
		assertTrue(project.getProjectMaterials().size() > 0);
	}

}

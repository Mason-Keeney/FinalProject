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

class UserTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.clear();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("acadmin", user.getUsername());

	}

	@Test
	void test_User_Address_mapping() {
		assertNotNull(user);
		assertNotNull(user.getAddress());
		assertEquals("123 chase lane", user.getAddress().getStreet1());
	}

	@Test
	void test_User_Projects_mapping() {
		assertNotNull(user);
		assertNotNull(user.getOwnedProjects());
		assertTrue(user.getOwnedProjects().size() > 0);
	}

	@Test
	void test_User_Tools_mapping() {
		assertNotNull(user);
		assertNotNull(user.getTools());
		assertTrue(user.getTools().size() > 0);
	}

	@Test
	void test_User_ProjectComments_mapping() {
		assertNotNull(user);
		assertNotNull(user.getProjectComments());
		assertTrue(user.getProjectComments().size() > 0);
	}

	@Test
	void test_User_ToolComment_mapping() {
		assertNotNull(user);
		assertNotNull(user.getToolComments());
		assertTrue(user.getToolComments().size() > 0);
	}

	@Test
	void test_User_Participations_mapping() {
		assertNotNull(user);
		assertNotNull(user.getParticipations());
		assertTrue(user.getParticipations().size() > 0);
	}

	@Test
	void test_User_Owned_Projects_mapping() {
		assertNotNull(user);
		assertNotNull(user.getOwnedProjects());
		assertTrue(user.getOwnedProjects().size() > 0);
	}

}

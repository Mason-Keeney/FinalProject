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
		System.err.println("\n reached setUp");
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		System.err.println("reached teardown");
		em.close();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		System.err.println("reached User Entity");
		assertNotNull(user);
		assertEquals("acadmin", user.getUsername());
		System.err.println("finished User Entity");

	}

	@Test
	void test_User_Address_mapping() {
		System.err.println("reached User-Address");
		assertNotNull(user);
		assertNotNull(user.getAddress());
		assertEquals("123 chase lane", user.getAddress().getStreet1());
		System.err.println("finished User-Address");
	}

	@Test
	void test_User_Projects_mapping() {
		System.err.println("reached User-Projects");
		assertNotNull(user);
		assertNotNull(user.getOwnedProjects());
		assertTrue(user.getOwnedProjects().size() > 0);
		System.err.println("finished User-Projects");
	}

	@Test
	void test_User_Tools_mapping() {
		System.err.println("reached User-Tools");
		assertNotNull(user);
		assertNotNull(user.getTools());
		assertTrue(user.getTools().size() > 0);
		System.err.println("finished User-Tools");
	}

	@Test
	void test_User_ProjectComments_mapping() {
		System.err.println("reached User-ProjectComments");
		assertNotNull(user);
		assertNotNull(user.getProjectComments());
		assertTrue(user.getProjectComments().size() > 0);
		System.err.println("finished User-ProjectComments");
	}

	@Test
	void test_User_ToolComment_mapping() {
		System.err.println("reached User-ToolComments");
		assertNotNull(user);
		assertNotNull(user.getToolComments());
		assertTrue(user.getToolComments().size() > 0);
		System.err.println("finished User-ToolComments");
	}

	@Test
	void test_User_Participations_mapping() {
		System.err.println("reached User-Participations");
		assertNotNull(user);
		assertNotNull(user.getParticipations());
		assertTrue(user.getParticipations().size() > 0);
		System.err.println("finished User-Participation");
	}

	@Test
	void test_User_Owned_Projects_mapping() {
		System.err.println("reached User-OwnedProjects");
		assertNotNull(user);
		assertNotNull(user.getOwnedProjects());
		assertTrue(user.getOwnedProjects().size() > 0);
		System.err.println("finished User-OwnedProjects");
	}

}

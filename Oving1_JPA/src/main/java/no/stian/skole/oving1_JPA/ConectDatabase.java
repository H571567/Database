package no.stian.skole.oving1_JPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import no.stian.skole.oving1_JPA.types.EntityBruker;
import no.stian.skole.oving1_JPA.types.EntityTest;

public class ConectDatabase {
	  private static final String PERSISTENCE_UNIT_NAME = "brukerPersistenceUnit";
	    private static EntityManagerFactory factory;

	 public static void main(String[] args) {
	        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        EntityManager em = factory.createEntityManager();
	        // read the existing entries and write to console
	        Query q = em.createQuery("select b from EntityBruker b");
	        List<EntityBruker> users = q.getResultList();
	        for (EntityBruker user : users) {
	            System.out.println(user);
	            user.setFirstname("baretull");
	            em.getTransaction().begin();
	            em.persist(user);
	            em.getTransaction().commit();
	            
	        }
	        System.out.println("Size: " + users.size());
	       
	        Query q2 = em.createQuery("select b from EntityTest b");
	        List<EntityTest> usersT = q2.getResultList();
	        for (EntityTest user : usersT) {
	            System.out.println(user);
	        }
	        System.out.println("Size: " + usersT.size());
	        
	        //EntityTest ny= new EntityTest();
	        //ny.setNoe("StianKvamme");
	        //ny.setNoeAnnet("asasd");
	        
	        EntityTest ny = em.find(EntityTest.class, "heipaadeg");
	        if(ny==null) {
	        	ny=new EntityTest();
	        	ny.setNoe("heipaadeg");
	        }
	        ny.setNoeAnnet("hei");
	      //leger til et objekt i test databasen 
	        em.getTransaction().begin();
	        em.persist(ny);
	        em.getTransaction().commit();
	      // 
	        // create new todo
//	        em.getTransaction().begin();
//	        em.persist(todo);
//	        em.getTransaction().commit();

	        em.close();
	    }
}

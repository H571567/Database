package no.stian.skole.oving1_JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import no.stian.skole.oving1_JPA.types.EntityAnsatt;
import no.stian.skole.oving1_JPA.types.EntityProsjekt;

public class ConnectDBProsjekt {
	private static final String PERSISTENCE_UNIT_NAME = "brukerPersistenceUnit";
	private static EntityManager em = null;
	
	public EntityManager connetctEntityManager() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em=factory.createEntityManager();
		return em;
	}
	public void closeEntityManager() {
		if(em!=null) {
			em.close();
		}
	}
	public void leggTilProsjekt(String navn, String beskrivelse) {
		this.connetctEntityManager();
		EntityProsjekt ny=new EntityProsjekt();
		ny.setNavn(navn);
		ny.setBeskrivelse(beskrivelse);
		em.getTransaction().begin();
		em.persist(ny);
		em.getTransaction().commit();
	}
	public static void main(String[] args) {
		ConnectDBProsjekt koble= new ConnectDBProsjekt();
		koble.connetctEntityManager();
		koble.leggTilProsjekt("ProsjektA3", "Vi skal bygge hus");
		koble.closeEntityManager();
	}
	public EntityProsjekt finnProsject(int prosjektid) {
	
		connetctEntityManager();
		EntityProsjekt funnet = null;
		funnet = em.find(EntityProsjekt.class, prosjektid);
		return funnet;
		
	}
}

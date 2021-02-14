package no.stian.skole.oving1_JPA;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import no.stian.skole.oving1_JPA.types.EntityAnsatt;

public class ConectDatabaseAnsatt {
	private static final String PERSISTENCE_UNIT_NAME = "brukerPersistenceUnit";
	protected static EntityManager em=null;
	public static EntityManager getEntityManager() {
		if(em==null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();
		}
		return em;
	}
	public static void closeEntityManager() {
		if(em!=null) {
			em.close();
		}
	}

	public static void main(String[] args) {
		ConectDatabaseAnsatt koble = new ConectDatabaseAnsatt();
		EntityAnsatt funnet = null;
		koble.leggTilIAnsatt("SK3", "Stian", "Kvamme", null, null, -1,"A2");
		koble.leggTilIAnsatt("SK1", "Stian", "Kvamme", null, null, -1,"A2");
		koble.leggTilIAnsatt("CK", "Camilla", "Kvamme",null, null, -1,"A1");
		funnet = koble.finnVedId(1);
		koble.finnVedBrukernavn("SK1");
		System.out.println(funnet);
		koble.EntityOppdaterAnsattLonn(1, 1000);
		koble.EntityOppdaterAnsattAvdeling(1, "A5");
		koble.EntityOppdaterAnsattAvdeling(4, "A3");
		koble.EntityInfoAnsatte();
		
		//ConectDatabaseAnsatt.closeEntityManager();

	}

	public EntityAnsatt finnVedId(int ansattNr) {
		// read the existing entries and write to console
		ConectDatabaseAnsatt.getEntityManager();
		EntityAnsatt funnet = null;
		funnet = em.find(EntityAnsatt.class, ansattNr);
		//ConectDatabaseAnsatt.closeEntityManager();
		return funnet;
	}
	public List<EntityAnsatt> finnVedBrukernavn(String brukernavn) {
		ConectDatabaseAnsatt.getEntityManager();
		Query q = em.createQuery("select b from EntityAnsatt b where b.brukernavn=:bruker");
//		Query q = em.createQuery("select b from EntityAnsatt b"); henter alle i datene i databasen
		q.setParameter("bruker",brukernavn);
		List<EntityAnsatt> users = q.getResultList();
		//ConectDatabaseAnsatt.closeEntityManager();
		return users;
	}
	public void EntityInfoAnsatte() {
		ConectDatabaseAnsatt.getEntityManager();
		Query q= em.createQuery("select b from EntityAnsatt b");
		List<EntityAnsatt> ansatte=q.getResultList();
		if(ansatte!=null) {
			for(EntityAnsatt ansatt : ansatte) {
				System.out.println(ansatt.toString());
			}
		}else {
			System.out.println("Ingen ansatte");
		}
		//ConectDatabaseAnsatt.closeEntityManager();
	}
	public EntityAnsatt EntityOppdaterAnsattLonn(int ansattNr,float maandeslonn) {
		ConectDatabaseAnsatt.getEntityManager();
		EntityAnsatt ansatt=null;
		ansatt= em.find(EntityAnsatt.class, ansattNr);
		if(ansatt!=null) {
			ansatt.setMaanedslonn(maandeslonn);
		}
		//ConectDatabaseAnsatt.closeEntityManager();
		return ansatt;
	} 
	public EntityAnsatt EntityOppdaterAnsattAvdeling(int ansattNr,String avdeling) {
		ConectDatabaseAnsatt.getEntityManager();
		EntityAnsatt ansatt=null;
		ansatt= em.find(EntityAnsatt.class, ansattNr);
		if(ansatt!=null) {
			if(ansatt.getStilling()!=null && !ansatt.getStilling().equals("sjef")) {
				ansatt.setAnsattavdeling(avdeling);
			}else{
				System.out.println("den ansatte er sjef for avdelingen, kan ikke oppdatere avdeling");
			}
		}
		//ConectDatabaseAnsatt.closeEntityManager();
		return ansatt;
	}
	public void leggTilIAnsatt(String brukernavn, String fornavn, String etternavn, Date ansattDato, String stilling,
			float maanedslonn, String avdeling) {
		ConectDatabaseAnsatt.getEntityManager();
		EntityAnsatt ny = new EntityAnsatt();
		ny.setBrukernavn(brukernavn);
		ny.setFirstname(fornavn);
		ny.setLastname(etternavn);
		ny.setAnsettelsesdato(ansattDato);
		ny.setStilling(stilling);
		ny.setMaanedslonn(maanedslonn);
		ny.setAnsattavdeling(avdeling);
		em.getTransaction().begin();
		em.persist(ny);
		em.getTransaction().commit();
		//ConectDatabaseAnsatt.closeEntityManager();
	}
	public void oppdaterAnsatt(EntityAnsatt sjef) {
		ConectDatabaseAnsatt.getEntityManager();
		if(sjef.getAnsattnr()>0) {
			em.getTransaction().begin();
			em.merge(sjef);
			em.getTransaction().commit();
		}
		
	}
}

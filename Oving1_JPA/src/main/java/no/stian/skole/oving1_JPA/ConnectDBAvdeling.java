package no.stian.skole.oving1_JPA;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import no.stian.skole.oving1_JPA.types.EntityAnsatt;
import no.stian.skole.oving1_JPA.types.EntityAvdeling;

public class ConnectDBAvdeling {
	private static final String PERSISTENCE_UNIT_NAME = "brukerPersistenceUnit";
	private static EntityManager em = null;

	public static EntityManager connectEntityManager() {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();
		}
		return em;
	}

	public void closeEntityManager() {
		if (em != null) {
			em.close();
		}
	}

	public EntityAnsatt finnAvdSjef(String avdelingsNavn) {
		ConnectDBAvdeling.connectEntityManager();
		String rolle = "sjef";
		EntityAnsatt sjef = null;
		Query q = em.createQuery("select b from EntityAnsatt b where b.ansattavdeling=:avd and b.stilling=:stil");
		q.setParameter("avd", avdelingsNavn);
		q.setParameter("stil", rolle);
		List<EntityAnsatt> ansatte = q.getResultList();
		if (ansatte.size() > 0) {
			sjef = ansatte.get(0);
		} else {
			System.out.println("det finnes ingen ansatte som er sjef for avdelingen " + avdelingsNavn + "\n");
		}

		return sjef;
	}

	public EntityAvdeling finnAvdeling(int avdelingsId) {
		ConnectDBAvdeling.connectEntityManager();
		Query q = em.createQuery("select b from EntityAvdeling b where b.avdelingid=:avd");
		q.setParameter("avd", avdelingsId);
		List<EntityAvdeling> avdeling = q.getResultList();
		if (avdeling != null && avdeling.size() > 0) {
			EntityAvdeling funnet = avdeling.get(0);
			return funnet;
		}
		return null;

	}

	public void utlistAnsatteForAvdeling(int avdelingsID) {
		ConnectDBAvdeling.connectEntityManager();
		ConectDatabaseAnsatt.getEntityManager();
		EntityAvdeling avdeling = finnAvdeling(avdelingsID);
		if (avdeling != null) {
			String avdNavn = avdeling.getNavn();
			Query q = em.createQuery("select b from EntityAnsatt b where b.ansattavdeling=:avdNavn");// select * from																						// ansattavdeling="A2"
			q.setParameter("avdNavn", avdNavn);
			List<EntityAnsatt> ansatteIAVD = q.getResultList();
			if (ansatteIAVD != null) {
				for (int i = 0; i < ansatteIAVD.size(); i++) {
					if(ansatteIAVD.get(i).getStilling()!=null && ansatteIAVD.get(i).getStilling().equals("sjef")) {
						System.out.print("Dette er sjefen for avdelingen:   ");
					}
					System.out.println(ansatteIAVD.get(i));
				}
			} else {
				System.out.println("Ingen ansatte i avdelingen: " + avdNavn);
			}
		}
	}

	public void leggTilAvdeling(String navn, int ansattsjef) {
		ConnectDBAvdeling.connectEntityManager();
		EntityAvdeling ny = new EntityAvdeling();
		if (finnAvdelingNavn(navn) == null) {
			ny.setNavn(navn);
			int ansNr = ansattsjef;
			EntityAnsatt mid = finnAvdSjef(navn);
			if (mid != null) {
				ny.setAnsattsjef(mid.getAnsattnr());
			} else {
				ansNr = setAvdSjef(navn,ansattsjef);
				ny.setAnsattsjef(ansNr);
			}
			
			em.getTransaction().begin();
			em.persist(ny);
			em.getTransaction().commit();
		}
	}

	public EntityAvdeling finnAvdelingNavn(String avdNavn) {
		ConnectDBAvdeling.connectEntityManager();
		Query q = em.createQuery("select b from EntityAvdeling b where b.navn=:avdNavn");
		q.setParameter("avdNavn", avdNavn);
		List<EntityAvdeling> avdeling = q.getResultList();
		if (avdeling != null && avdeling.size() > 0) {
			EntityAvdeling funnet = avdeling.get(0);
			return funnet;
		}
		return null;

	}
	
	private int setForsteSomAvdSjef(String avdNavn) {
		ConectDatabaseAnsatt.getEntityManager();
		ConnectDBAvdeling.connectEntityManager();
		Query q2 = em.createQuery("select b from EntityAnsatt b where b.stilling <> 'sjef' or b.stilling is null");
		List<EntityAnsatt> ansatte = q2.getResultList();
		if (ansatte.size()>0) {
			EntityAnsatt ny=ansatte.get(0);
			ny.setAnsattavdeling(avdNavn);
			ny.setStilling("sjef");
			ConectDatabaseAnsatt.em.getTransaction().begin();
			ConectDatabaseAnsatt.em.merge(ny);
			ConectDatabaseAnsatt.em.getTransaction().commit();
			return ny.getAnsattnr();
		}
		return -1;
	}

	private int setAvdSjef(String avdNavn, int sjefID) {
		ConectDatabaseAnsatt ansatte= new ConectDatabaseAnsatt();
		EntityAnsatt sjef=ansatte.finnVedId(sjefID);
		if(sjef!=null && (sjef.getStilling()==null || !sjef.getStilling().equals("sjef"))) {
			sjef.setAnsattavdeling(avdNavn);
			sjef.setStilling("sjef");
			ansatte.oppdaterAnsatt(sjef);
			return sjef.getAnsattnr();
		}else {
			return setForsteSomAvdSjef(avdNavn);
			
		}
		
	}

	public static void main(String[] args) {
		ConnectDBAvdeling koble = new ConnectDBAvdeling();
		ConnectDBAvdeling.connectEntityManager();
		koble.leggTilAvdeling("A2", 1);
		koble.leggTilAvdeling("A1", 3);
		koble.utlistAnsatteForAvdeling(3);// feil i denne metoden sin spørring
		koble.leggTilAvdeling("A5", -1);
		koble.finnAvdeling(3);
	}
}

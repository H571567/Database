package no.stian.skole.oving1_JPA;

import java.math.BigDecimal;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import no.stian.skole.oving1_JPA.types.EntityAnsatt;
import no.stian.skole.oving1_JPA.types.EntityProsjekt;
import no.stian.skole.oving1_JPA.types.Entityansattprosjekt;

public class ConnectDBAnsattProsjekt {
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
	public void registrerProsjektDeltagelse(int ansattnr, int prosjektid, String rolle, double timer) {
		this.connetctEntityManager();
		Entityansattprosjekt ny= new Entityansattprosjekt();
		ConectDatabaseAnsatt ansattInfo=new ConectDatabaseAnsatt();
		ny.setAnsatte(ansattInfo.finnVedId(ansattnr));
		ConnectDBProsjekt prosjectInfo = new ConnectDBProsjekt();
		EntityProsjekt prosjekt = prosjectInfo.finnProsject(prosjektid);
		ny.setProsjekter(prosjekt);
		ny.setRolle(rolle);
		//BigDecimal bigDecimalCurrency=new BigDecimal(timer);
		ny.setTimer(timer);
		em.getTransaction().begin();
		em.persist(ny);
		em.getTransaction().commit();
	}
	public void oppdaterTimerForAnsatt(int ansattprosjektid, double timer) {
		connetctEntityManager();
		Entityansattprosjekt funnet=finnAnsattProsjektVedID(ansattprosjektid);
		if(funnet!=null ) {
			funnet.setTimer(timer);
			em.getTransaction().begin();
			em.merge(funnet);
			em.getTransaction().commit();
		}
	}
	public Entityansattprosjekt finnAnsattProsjektVedID(int ansattprosjektid) {
		connetctEntityManager();
		Entityansattprosjekt funnet = null;
		funnet = em.find(Entityansattprosjekt.class, ansattprosjektid);
		return funnet;
	}
	public void utskriftInfoProsjekt(int posjektid) {
		this.connetctEntityManager();
		javax.persistence.Query q= em.createQuery("select b from Entityansattprosjekt b where b.prosjekter.prosjektid=:prosjekt");//denne erfeil

		q.setParameter("prosjekt", posjektid);
		List<Entityansattprosjekt> ansatteProsjekt=q.getResultList();
		if(ansatteProsjekt.size()>0) {
			double totTimer=0;
			for(int i=0; i<ansatteProsjekt.size(); i++) {
				totTimer=totTimer+ansatteProsjekt.get(i).getTimer();
				System.out.println(ansatteProsjekt.get(i).toString());
			}
			System.out.println("Totalt antall timer på prosjektet er : "+ totTimer);
		}
	}
	public static void main(String[] args) {
		ConnectDBAnsattProsjekt koble = new ConnectDBAnsattProsjekt();
		koble.connetctEntityManager();
		//koble.oppdaterTimerForAnsatt(4, 55.7);
		//koble.registrerProsjektDeltagelse(2, 1, "Rørlegger", 40);
		koble.utskriftInfoProsjekt(1);
		koble.closeEntityManager();
	}
}

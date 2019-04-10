package no.hvl.dat107.eao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Prosjekt;

public class ProsjektEAO {
	private EntityManagerFactory emf;
	
	
	public ProsjektEAO() {
		emf = Persistence.createEntityManagerFactory("ansattPU");
	}
	
	public void leggTilNyttProsjekt(Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(prosjekt);
			tx.commit();
			
		}catch(Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close();
		}
	}
	
	public Prosjekt finnProsjektMedId(int prosjektId) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Prosjekt.class, prosjektId);
		} finally {
			em.close();
		}
	}
	
	
	

}

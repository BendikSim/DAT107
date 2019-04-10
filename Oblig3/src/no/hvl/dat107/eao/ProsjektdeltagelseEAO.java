package no.hvl.dat107.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class ProsjektdeltagelseEAO {
private EntityManagerFactory emf;
	
	
	public ProsjektdeltagelseEAO() {
		emf = Persistence.createEntityManagerFactory("ansattPU");
	}
	
	public Prosjektdeltagelse finnProsjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Prosjektdeltagelse> query = em.createQuery("SELECT p FROM Prosjektdeltagelse p WHERE p.ansatt = :ansatt AND p.prosjekt = :prosjekt",
					Prosjektdeltagelse.class);
			query.setParameter("ansatt", ansatt);
			query.setParameter("prosjekt", prosjekt);
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
	
	public void registrerProsjektdeltagelse(Prosjektdeltagelse pd) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
            em.persist(pd);
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
	
	public void oppdaterProsjektdeltagelse(Prosjektdeltagelse pd) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.merge(pd);
			em.merge(pd.getAnsatt());
			em.merge(pd.getProsjekt());
			tx.commit();
		}catch (Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close();
		}
}
	
	public List<Prosjektdeltagelse> prosjektDeltagelseInfo(Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();
		List<Prosjektdeltagelse> ansattDeltagelse;

		try {
			TypedQuery<Prosjektdeltagelse> query = em.createQuery(
					"SELECT pd from Prosjektdeltagelse pd WHERE pd.prosjekt = :prosjekt", Prosjektdeltagelse.class);
			query.setParameter("prosjekt", prosjekt);
			ansattDeltagelse = query.getResultList();
		} finally {
			em.close();
		}

		return ansattDeltagelse;
	}
	
	

}

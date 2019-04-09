package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AvdelingEAO {
	
    private EntityManagerFactory emf;
	
	public AvdelingEAO () {
		emf = Persistence.createEntityManagerFactory("ansattPU");
	}
	
	public Avdeling finnAvdelingmedId(int id) {
          EntityManager em = emf.createEntityManager();
		
		Avdeling a;
		
		try {
			a = em.find(Avdeling.class, id);
		}finally {
			em.close();
			
		}
		return a;
	}
	
	public List<Ansatt> utlistingAnsatteVedAvdeling(int avdId){
		EntityManager em = emf.createEntityManager();
		
		List<Ansatt> ansatte;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.avdId = :avdId", Ansatt.class);
			query.setParameter("avdId", avdId);
			ansatte = query.getResultList();
			
		}finally {
			em.close();
		}
		return ansatte;
	}
	
	public void oppdaterAvdeling(Avdeling a) {
			
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			try {
				tx.begin();
				em.merge(a);
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
	
	public void leggTilNyAvdeling(Avdeling a) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(a);
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
	
	

}

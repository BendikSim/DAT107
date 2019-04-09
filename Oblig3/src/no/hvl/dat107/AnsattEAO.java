package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AnsattEAO {
	
	private EntityManagerFactory emf;
	
	public AnsattEAO () {
		emf = Persistence.createEntityManagerFactory("ansattPU");
	}
	
	public Ansatt finnAnsattMedId(int ansattId) {
		 
		EntityManager em = emf.createEntityManager();
		
		Ansatt s;
		
		try {
			s = em.find(Ansatt.class, ansattId);
		}finally {
			em.close();
			
		}
		return s;
	}
	
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
		
		EntityManager em = emf.createEntityManager();
		Ansatt a;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a from Ansatt a WHERE a.brukernavn LIKE :brukernavn", Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			a = query.getSingleResult();
		}finally {
			em.close();
		}
		return a;
	}
	
	public List<Ansatt> finnAlleAnsatte(){
		EntityManager em = emf.createEntityManager();
		
		List<Ansatt> ansatte;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
			ansatte = query.getResultList();
			
			
		}finally {
			em.close();
		}
		return ansatte;
		
		
	}
	public void oppdaterAnsatt(Ansatt ansatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.merge(ansatt);
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
	
	public void leggTilNyAnsatt(Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(ansatt);
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

package no.hvl.dat107.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (schema = "oblig3", name = "ansatt")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattId;
	
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansdato;
	private String stilling;
	private int maanedslonn;
	
	@OneToOne(mappedBy = "sjef")
	private Avdeling sjefFor;
	
	
	@ManyToOne
	@JoinColumn(name = "avdId", referencedColumnName = "avdId")
	private Avdeling avdeling;
	
	@OneToMany(mappedBy="ansatt")
    private List<Prosjektdeltagelse> deltagelser;
	
	public Ansatt() {}
	
	public Ansatt(String brukernavn, String fornavn, String etternavn, 
		      LocalDate ansettelsesDato, String stilling, int maanedslonn, Avdeling avdeling) {
	this.brukernavn = brukernavn;
	this.fornavn = fornavn;
	this.etternavn = etternavn;
	this.ansdato = ansettelsesDato;
	this.stilling = stilling;
	this.maanedslonn = maanedslonn;
	this.avdeling = avdeling;
	}
	
	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }
	
	public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }

	
	public int getAnsattId() {
		return ansattId;
	}

	public void setAnsattId(int ansattId) {
		this.ansattId = ansattId;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsettelsesDato() {
		return ansdato;
	}

	public void setAnsettelsesDato(LocalDate ansettelsesDato) {
		this.ansdato = ansettelsesDato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public int getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(int maanedslonn) {
		this.maanedslonn = maanedslonn;
	}
	
	public Avdeling getAvd() {
		return avdeling;
	}

	public void setAvd(Avdeling avdeling) {
		this.avdeling = avdeling;
	}
	
	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void setDeltagelser(List<Prosjektdeltagelse> deltagelser) {
		this.deltagelser = deltagelser;
	}
	
	
	public String toString() {
		return "AnsattID: " + this.ansattId + "\n" + "Brukernavn : " + this.brukernavn + 
					"\n" + "Navn : " + this.fornavn + "\n" + "Etternavn : " + this.etternavn + 
					"\n" + "Stilling : " + this.stilling + "\n" + "Maandeslonn : " + this.maanedslonn + 
					"\n" + "AvdelingId : " + avdeling.getAvdId();
		}	

	public void skrivUt() {
		System.out.println(toString());
	}
   

}

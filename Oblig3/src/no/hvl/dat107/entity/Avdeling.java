package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3", name = "avdeling")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdId;
	private String navn;
	
	@OneToOne
	@JoinColumn(name="sjef", referencedColumnName = "ansattId")
	private Ansatt sjef;

	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
	private List <Ansatt> ansatte;
	
	public Avdeling() {}
	
	public Avdeling(String navn, Ansatt sjef) {
		this.navn = navn;
		this.sjef = sjef;
	}

	public int getAvdId() {
		return avdId;
	}

	public void setAvdId(int avdId) {
		this.avdId = avdId;
	}
	
	public Ansatt getSjef() {
		return sjef;
	}

	public void setSjef(Ansatt Sjef) {
		this.sjef = Sjef;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public List<Ansatt> getAnsatte() {
		return ansatte;
	}

	public void setAnsatte(List<Ansatt> ansatte) {
		this.ansatte = ansatte;
	}
	
	
	public String toString() {
		return "Avdeling [avdId = " + avdId + ", navn = " + navn + ", sjef = " + sjef.getFornavn() + " " + sjef.getEtternavn() + "]";
	}
	
	public void skrivUt() {
		System.out.println(toString());
	}
	

}

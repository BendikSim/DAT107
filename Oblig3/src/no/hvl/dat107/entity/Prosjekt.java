package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3", name = "prosjekt")
public class Prosjekt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektId;
	
	private String navn;
	private String beskrivelse;
	
	@OneToMany(mappedBy="prosjekt")
    private List<Prosjektdeltagelse> deltagelser;
	
	public Prosjekt() {}
	
	public Prosjekt(String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}
	
	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }
	
	 public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
	    deltagelser.remove(prosjektdeltagelse);
	 }

	public int getProsjektId() {
		return prosjektId;
	}

	public void setProsjektId(int prosjektId) {
		this.prosjektId = prosjektId;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void setDeltagelser(List<Prosjektdeltagelse> deltagelser) {
		this.deltagelser = deltagelser;
	}
	
	@Override
	public String toString() {
		return "Prosjekt [prosjektId = " + prosjektId + ", navn = " + navn + ", beskrivelse = " + beskrivelse + "]";
	}

}

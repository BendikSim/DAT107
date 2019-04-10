package no.hvl.dat107.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3", name = "prosjektdeltagelse")
public class Prosjektdeltagelse {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektdeltagelseId;
	
	@ManyToOne
	@JoinColumn(name = "ansattid")
	private Ansatt ansatt;
	
	@ManyToOne
	@JoinColumn(name = "prosjektid")
	private Prosjekt prosjekt;
	
	private String rolle;
	private double timer;
	
    public Prosjektdeltagelse() {}
	
	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.rolle = rolle;
		timer = 0.0;
		ansatt.leggTilProsjektdeltagelse(this);
        prosjekt.leggTilProsjektdeltagelse(this);
	}
	
	public Ansatt getAnsatt() {
		return ansatt;
	}

	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public Prosjekt getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public double getTimer() {
		return timer;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}

	@Override
	public String toString() {
		return "Prosjektdeltagelse [ansattid =" + ansatt.getAnsattId() + ", prosjektid=" + prosjekt.getProsjektId() +
			   ", rolle=" + rolle + ", timer=" + timer + "]";
	}
	
}

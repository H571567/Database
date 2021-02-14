package no.stian.skole.oving1_JPA.types;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prosjekt", schema="oving1_jpa")
public class EntityProsjekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int prosjektid;
	private String navn;
	private String beskrivelse;
	public int getProsjektid() {
		return prosjektid;
	}
	public void setProsjektid(int prosjektid) {
		this.prosjektid = prosjektid;
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
	@Override
	public String toString() {
		return "EntityProsjekt [prosjektid=" + prosjektid + ", navn=" + navn + ", beskrivelse=" + beskrivelse + "]";
	}
	
}

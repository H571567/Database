package no.stian.skole.oving1_JPA.types;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="avdeling", schema="oving1_jpa")
public class EntityAvdeling {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int avdelingid;
	private String navn;
	private int ansattsjef;
	public int getAvdelingid() {
		return avdelingid;
	}
	public void setAvdelingid(int avdelingid) {
		this.avdelingid = avdelingid;
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public int getAnsattsjef() {
		return ansattsjef;
	}
	public void setAnsattsjef(int ansattsjef) {
		this.ansattsjef = ansattsjef;
	}
	@Override
	public String toString() {
		return "EntityAvdeling [avdelingid=" + avdelingid + ", navn=" + navn + ", ansattsjef=" + ansattsjef + "]";
	}
	
}

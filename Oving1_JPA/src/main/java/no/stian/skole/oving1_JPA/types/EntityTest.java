package no.stian.skole.oving1_JPA.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test", schema="oving1_jpa")
public class EntityTest {
	@Id
	@Column(name="brukernavn")
	private String noe;
	@Column(name="firstname")
	private String noeAnnet;
	public String getNoe() {
		return noe;
	}
	public void setNoe(String noe) {
		this.noe = noe;
	}
	public String getNoeAnnet() {
		return noeAnnet;
	}
	public void setNoeAnnet(String noeAnnet) {
		this.noeAnnet = noeAnnet;
	}
	@Override
	public String toString() {
		return "EntityTest [noe=" + noe + ", noeAnnet=" + noeAnnet + "]";
	}
	
	
}

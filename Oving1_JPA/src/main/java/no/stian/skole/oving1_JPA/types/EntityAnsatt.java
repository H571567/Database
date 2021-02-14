package no.stian.skole.oving1_JPA.types;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name= "ansatt", schema= "oving1_jpa")
public class EntityAnsatt{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ansattnr;
	private String brukernavn;
	private String firstname;
	private String lastname; 
    private Date ansettelsesdato;
    private String stilling;
    private float maanedslonn;
    private String ansattavdeling;
	public int getAnsattnr() {
		return ansattnr;
	}
	public void setAnsattnr(int ansattnr) {
		this.ansattnr = ansattnr;
	}
	
	public String getBrukernavn() {
		return brukernavn;
	}
	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getAnsettelsesdato() {
		return ansettelsesdato;
	}
	public void setAnsettelsesdato(java.util.Date ansattDato) {
		this.ansettelsesdato = (Date) ansattDato;
	}
	public String getStilling() {
		return stilling;
	}
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	public float getMaanedslonn() {
		return maanedslonn;
	}
	public void setMaanedslonn(float maanedslonn) {
		this.maanedslonn = maanedslonn;
	}
	public String getAnsattavdeling() {
		return ansattavdeling;
	}
	public void setAnsattavdeling(String ansattavdeling) {
		this.ansattavdeling = ansattavdeling;
	}
	
	@Override
	public String toString() {
		return "EntityAnsatt [ansattnr=" + ansattnr + ", brukernavn=" + brukernavn + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", ansettelsesdato=" + ansettelsesdato + ", stilling=" + stilling
				+ ", maanedslonn=" + maanedslonn + ", avdeling=" + ansattavdeling + "]";
	}
}

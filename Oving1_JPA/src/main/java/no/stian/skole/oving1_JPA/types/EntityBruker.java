package no.stian.skole.oving1_JPA.types;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name= "bruker", schema= "oving1_jpa")
public class EntityBruker{
	@Id
	private String brukernavn;
	private String firstname;
	private String lastname; 
    private Date ansettelsesdato;
    private String stilling;
    private float maanedslonn;
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
	public void setAnsettelsesdato(Date ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
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
	@Override
	public String toString() {
		return "EntityBruker [brukernavn=" + brukernavn + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", ansettelsesdato=" + ansettelsesdato + ", stilling=" + stilling + ", maanedslonn=" + maanedslonn
				+ "]";
	}
	
	
    
}

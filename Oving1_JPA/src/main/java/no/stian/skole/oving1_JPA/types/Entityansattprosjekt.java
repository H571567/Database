package no.stian.skole.oving1_JPA.types;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.jpa.config.Cascade;

@Entity
@Table(name= "ansattprosjekt", schema= "oving1_jpa")
public class Entityansattprosjekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int ansattprosjektid;
	
	@ManyToOne
	@JoinColumn(name="ansattnr")
	private EntityAnsatt ansatte;
	
	@ManyToOne
	@JoinColumn(name="prosjektid")
	private EntityProsjekt prosjekter;
	
	private String rolle;
	private double timer;
	
	public String getRolle() {
		return rolle;
	}
	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	public void setTimer(double timer) {
		// TODO Auto-generated method stub
		this.timer = timer;
	}
	public double getTimer() {
		return timer;
	}
	
	public int getAnsattprosjektid() {
		return ansattprosjektid;
	}
	public void setAnsattprosjektid(int ansattprosjektid) {
		this.ansattprosjektid = ansattprosjektid;
	}
	public EntityAnsatt getAnsatte() {
		return ansatte;
	}
	public void setAnsatte(EntityAnsatt ansatte) {
		this.ansatte = ansatte;
	}
	public EntityProsjekt getProsjekter() {
		return prosjekter;
	}
	public void setProsjekter(EntityProsjekt prosjekter) {
		this.prosjekter = prosjekter;
	}
	@Override
	public String toString() {
		return "Entityansattprosjekt [ansattprosjektid=" + ansattprosjektid + ", ansatte=" + ansatte + ", prosjekter="
				+ prosjekter + ", rolle=" + rolle + ", timer=" + timer + "]";
	}
	

}

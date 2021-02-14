package no.stian.skole.oving1_JPA.types;

import java.util.List;
import java.util.Scanner;

import no.stian.skole.oving1_JPA.ConnectDBAnsattProsjekt;
import no.stian.skole.oving1_JPA.ConnectDBProsjekt;

public class KlientAnsattprosjekt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectDBAnsattProsjekt kobleAP= new ConnectDBAnsattProsjekt();
		ConnectDBProsjekt kobleP= new ConnectDBProsjekt();
		Scanner in = new Scanner(System.in);
		    System.out.print("Hva vil du gjøre? tast nr for operasjon \n"
		    		+ "1: registere nytt prosjekt \n"
		    		+ "2: registrere prosjektdeltagelse \n"
		    		+ "3: oppdatere timer for ansatt\n"
		    		+ "4: finne prosjekt\n"
		    		+ "5: utliske info om prosjekt\n");
		    int tall= Integer.parseInt(in.nextLine());
		    switch(tall) {
	    	case 1: 
	    		System.out.println("Skriv inn: et navn,beskrivelse(separer med ,(komma))");
	    		String[] verdier=in.nextLine().split(",");
	    		if(verdier!=null) {
	    			kobleP.leggTilProsjekt(verdier[0], verdier[1]);
	    		}
	    		break;
	    	case 2:
	    		System.out.println("Skriv inn: ansattnr,prosjektid,rolle,timer separer med ,(komma");
	    		String[] v=in.nextLine().split(",");
	    		if(v!=null) {
	    			kobleAP.registrerProsjektDeltagelse(Integer.parseInt(v[0]),Integer.parseInt(v[1]),v[2], Double.parseDouble(v[3]));
	    		}
	    		break;
	    	case 3:
	    		System.out.print("Skriv inn: ansattprosjektid,timer separer med ,(komma)");
	    		String[] verd=in.nextLine().split(",");
	    		if(verd!=null) {
	    			kobleAP.oppdaterTimerForAnsatt(Integer.parseInt(verd[0]), Double.parseDouble(verd[1]));
	    		}
	    		break;
	    	case 4:
	    		System.out.print("Skriv inn: ansattprosjektid");
	    		int id= Integer.parseInt(in.nextLine());
	    		if(id>0) {
	    			kobleAP.finnAnsattProsjektVedID(id);
	    		}
	    		break;
	    	case 5:
	    		System.out.print("Skriv inn: prosjektid");
	    		int pId= Integer.parseInt(in.nextLine());
	    		if(pId>0) {
	    			kobleAP.utskriftInfoProsjekt(pId);
	    		}
	    	default:
	    		System.out.println("Ikke et alternativ");
		    }
		    in.close();
    		kobleAP.closeEntityManager();
    		kobleP.closeEntityManager();
	}
}

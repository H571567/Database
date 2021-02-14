package no.stian.skole.oving1_JPA.types;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import no.stian.skole.oving1_JPA.ConectDatabaseAnsatt;

public class KlientAnsatt {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		 ConectDatabaseAnsatt ansattDB= new ConectDatabaseAnsatt();
		 Scanner in = new Scanner(System.in);
		    System.out.print("Hva vil du gjøre? tast nr for operasjon \n"
		    		+ "1: finn ansatt ved ansatt nr \n"
		    		+ "2: søk etter ansatt ved nrukernavn(initialer) \n"
		    		+ "3: Utlisting av alle ansatte \n"
		    		+ "4: oppdatere en ansatt sin lønn \n"
		    		+ "5: legg til ny ansatt\n"
		    		+ "6: sett hvlken avdeling en ansatt jobber i \n :");
		    String verdi = in.nextLine();
		    int Vnr= Integer.parseInt(verdi);
		    switch(Vnr) {
		    	case 1: 
		    		System.out.print("Skriv inn et ansattNR");
		    		int ansattNr=Integer.parseInt(in.nextLine());
		    		EntityAnsatt a=ansattDB.finnVedId(ansattNr);
		    		System.out.print(a.toString());
		    		break;
		    	case 2:
		    		System.out.print("Skriv inn et brukernavn");
		    		String brukernavn=in.nextLine();
		    		List<EntityAnsatt> ansattList=ansattDB.finnVedBrukernavn(brukernavn);
		    		for(EntityAnsatt b : ansattList) {
		    			System.out.print(b.toString());
		    		}
		    		break;
		    	case 3:
		    		ansattDB.EntityInfoAnsatte();
		    		break;
		    	case 4:
		    		System.out.print("Skriv inn et ansattNR");
		    		int id=Integer.parseInt(in.nextLine());
		    		EntityAnsatt funnet=ansattDB.finnVedId(id);
		    		System.out.print("Skriv inn en ny månedslønn");
		    		float f = Float.parseFloat(in.nextLine());
		    		if(funnet!=null) {
		    			funnet.setMaanedslonn(f);
		    			System.out.println("oppdatert Ansatt: "+ funnet.toString());
		    		}
		    		break;
		    	case 5: 
		    		System.out.print("Skriv inn info om ansatt, separer med skråstrek / i rekkefølgen: \n "
		    				+ "brukernavn/firstname/lastname/ansettelsesdato/stilling/maanedslonn/avdeling \n"
		    				+ "hvis en verdi mangler skriv ordet null, dato på format  yyyy-mm-dd");
		    		String ansatt= in.nextLine();
		    		String[] ansattverdier=ansatt.split("/");
		    		String brukernavnL=ansattverdier[0];
		    		String firstname=ansattverdier[1];
		    		String lastname=ansattverdier[2];
		    		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		    		Date dato=null;
				try {
					dato = dt1.parse(ansattverdier[3]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("feil, bruker dagens dato");
					dato= new Date();
					e.printStackTrace();
				}
		    	    String stilling=ansattverdier[4];
		    	    float maanedslonn=Float.parseFloat(ansattverdier[5]);
		    	    String avdeling=ansattverdier[6];
		    	    ansattDB.leggTilIAnsatt(brukernavnL, firstname,lastname, dato, stilling, maanedslonn, avdeling); 
		    	    break;
		    	case 6:
		    		System.out.println("Skriv inn ansattNr");
		    		int ansattnummer= Integer.parseInt(in.nextLine());
		    		System.out.println("Skriv inn avdelingsnavn");
		    		String avdelingsnavn=in.nextLine();
		    		ansattDB.EntityOppdaterAnsattAvdeling(ansattnummer, avdelingsnavn);
		    	default:
		    		in.close();
		    		
		    }
	}

}

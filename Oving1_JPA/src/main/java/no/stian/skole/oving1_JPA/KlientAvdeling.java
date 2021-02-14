package no.stian.skole.oving1_JPA;

import java.util.Scanner;

import no.stian.skole.oving1_JPA.types.EntityAnsatt;
import no.stian.skole.oving1_JPA.types.EntityAvdeling;

public class KlientAvdeling {

	public static void main(String[] args) {
		ConnectDBAvdeling koble= new ConnectDBAvdeling();
//		EntityAnsatt avdSjef=koble.finnAvdSjef("A1");
//		if(avdSjef!=null) {
//			System.out.println(avdSjef);
//		}
		//koble.leggTilAvdeling("A1", 3);
		 Scanner in = new Scanner(System.in);
		    System.out.print("Hva vil du gjøre? tast nr for operasjon \n"
		    		+ "1: finn avdeling ved avdelings id \n"
		    		+ "2: søk etter avdelingssjef ved avdelingsnavn \n"
		    		+ "3: Utlisting av alle ansatte for en avdeling ved avdellings id\n"
		    		+ "4: legg til avdeling \n"
		    		+ "5: sett avdelingssjef\n:");
		    String verdi = in.nextLine();
		    int Vnr= Integer.parseInt(verdi);
		    switch(Vnr) {
		    	case 1:
					System.out.println("Skriv inn avdelings id");
					int avdId = Integer.parseInt(in.nextLine());
					EntityAvdeling funnet = koble.finnAvdeling(avdId);
					if (funnet != null) {
						System.out.println(funnet);
					} else {
						System.out.println("Avdeling ikke funnet");
					}
					break;
		    	case 2:
		    		System.out.println("Skriv inn avdelingsnavn");
					String avdNavn=(in.nextLine());
					EntityAnsatt sjef=koble.finnAvdSjef(avdNavn);
					if (sjef != null) {
						System.out.println(sjef);
					} else {
						System.out.println("Avdelingsjef ikke funnet");
					}
					break;
		    	case 3:
		    		System.out.println("Skriv inn avdelings id");
					int avdelingsID = Integer.parseInt(in.nextLine());
		    		koble.utlistAnsatteForAvdeling(avdelingsID);
		    		break;
		    	case 4:
		    		System.out.println("skriv inn avdelingsnavn på den nye avdelingen /n");
					String nyAN=(in.nextLine());
					System.out.println("Skriv inn avdelingssjefen sin id");
					int nyID=Integer.parseInt(in.nextLine());	
					koble.leggTilAvdeling(nyAN, nyID);
					break;
		    	case 5: 
		    		System.out.println("Skriv inn ny avdelingsjef sin id");
		    	default: 
		    		in.close();
					
		    }
	}
}

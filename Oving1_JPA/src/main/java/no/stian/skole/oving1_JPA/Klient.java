package no.stian.skole.oving1_JPA;

import java.util.Scanner;

import no.stian.skole.oving1_JPA.types.KlientAnsatt;
import no.stian.skole.oving1_JPA.types.KlientAnsattprosjekt;

public class Klient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hva vil du gjøre operasjoner på \n"
				+ "1: ansatte \n"
				+ "2: avdeling \n"
				+ "3: prosjekt");
		Scanner in= new Scanner(System.in);
		int tall= Integer.parseInt(in.nextLine());
		switch(tall) {
		case 1:
			KlientAnsatt.main(args);
			break;
		case 2: 
			KlientAvdeling.main(args);
			break;
		case 3:
			KlientAnsattprosjekt.main(args);
		}
		
	}

}


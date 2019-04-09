package no.hvl.dat107;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Grensesnitt {
	
	private static AnsattEAO ansattEAO = new AnsattEAO();
	private static AvdelingEAO avdelingEAO = new AvdelingEAO();
	
	public static final Scanner leser = new Scanner(System.in);
	public static final String SKILLE = "--------------------";
	
//	public void start() {
//		System.out.println("----Meny----");
//		boolean ferdig = false;
//		while(!ferdig) {
//			ferdig = this.meny();
//		}
//	}
	private void operasjoner() {
		System.out.println("Mulige operasjoner \n");
		System.out.println(" 1 : Søk etter ansatte med ansattId");
		System.out.println(" 2 : Søk etter ansatte med brukernavn");
		System.out.println(" 3 : Utlisting av alle ansatte");
		System.out.println(" 4 : Oppdatere en ansatt sin stilling eller lønn");
		System.out.println(" 5 : Legge inn en ny ansatt");
		System.out.println(" 6 : Finn en avdeling med avdeling-ID");
		System.out.println(" 7 : Utlisting av alle ansatte ved avdeling");
		System.out.println(" 8 : Oppdater avdelingen til en ansatt");
		System.out.println(" 9 : Legge inn en ny avdeling");
		System.out.println(" 0 : Avslutt program");
	}
	
	public void meny() {
		
		int input = 1;
		boolean ferdig = false;
		do {
			
			System.out.println("-------Meny-------");
			
		    operasjoner();
		    input = Integer.parseInt(leser.nextLine());
			
		switch(input) {
		
		case 1 : System.out.println("Skriv inn ansattId");
		       int id = Integer.parseInt(leser.nextLine());
			   Ansatt an = ansattEAO.finnAnsattMedId(id);
			   an.skrivUt();
			   leser.nextLine();
			   break;
			   
		case 2 : System.out.println("Skriv inn brukernavn(Initialer) 3-4 bokstaver");
		        String bn = leser.nextLine();
		        Ansatt ab = ansattEAO.finnAnsattMedBrukernavn(bn);
		        ab.skrivUt();
		        leser.nextLine();
		       break;
		       
		case 3 : System.out.println("Utskrift av ansatte"); 
		         skrivUtAlleAnsatte();
		         leser.nextLine();
		       break;
		       
		case 4 : System.out.println("Skriv inn ansattId på den ansatten du vil oppdatere");
	            int ansid = Integer.parseInt(leser.nextLine());
		        oppdaterAnsatt2(leser, ansid);
		        leser.nextLine();
		       break;
		       
		case 5 : System.out.println("Legg til ny ansatt");   
		        ansattEAO.leggTilNyAnsatt(leggTil());
		        System.out.println("Ansatt lagt til");
		        leser.nextLine();
		       break;
		       
		case 6 : System.out.println("Skriv inn avdeling-ID");
		        int avdid = Integer.parseInt(leser.nextLine());
			    skrivUtAvdelingMedId(avdid);
			    leser.nextLine();
			   break;
			   
		case 7 : System.out.println("Skriv inn avdeling-ID");  
		        int avdid1 = Integer.parseInt(leser.nextLine());
	            skrivUtAvdeling(avdid1);
	            leser.nextLine();
	           break;
	           
		case 8 : System.out.println("Skriv inn ansatt-ID til den ansatte du vil oppdatere"); 
		         int ansId = Integer.parseInt(leser.nextLine());
		         System.out.println("Skriv inn Avdeling-ID til den nye avdelingen til den ansatte");
		         int avdId = Integer.parseInt(leser.nextLine());
		         Avdeling a = avdelingEAO.finnAvdelingmedId(avdId);
		         oppdaterAvdelingForAnsatt(ansId, a);
		         leser.nextLine();
		       break;
		case 9 : System.out.println("Legg inn en ny avdeling");
		           leggTilAvdeling();
		           leser.nextLine();
		         break;  
	
		case 0: System.out.println("Program avsluttet");
		        ferdig = true;
		       break;
		}
		}while(!ferdig);
		
		leser.close();
	}
	
	//metoder for Ansatt
	
	public static void skrivUtAlleAnsatte() {
		List<Ansatt> ansatte = ansattEAO.finnAlleAnsatte();
		for(Ansatt a : ansatte) {
			System.out.println(a);
			System.out.println();
		}
	}
	

	
	public void oppdaterAnsatt2(Scanner in, int ansattId) {
		Ansatt a = ansattEAO.finnAnsattMedId(ansattId);
		
		System.out.println("ønsker du å oppdatere stilling? ja/nei?");
		String svar = in.nextLine();
		if(svar.toLowerCase().equals("ja")) {
			System.out.println("Angi ny stilling: ");
			a.setStilling(in.nextLine());
		}
		
		System.out.println("ønsker du å oppdatere lønn? ja/nei?");
		String svar1 = in.nextLine();
			if(svar1.toLowerCase().equals("ja")) {
				int loenn = 0;
				System.out.println("Skriv inn ny månedslønn: ");
				loenn = in.nextInt();
				in.nextLine();
			 a.setMaanedslonn(loenn);
			}
			ansattEAO.oppdaterAnsatt(a);
			System.out.println("Ansatt oppdatert");
	}
	
	public Ansatt leggTil() {
	       String bnavn = null;
	       
	       System.out.println("Legger til ny ansatt");
	       System.out.println("Skriv inn brukernavn 3-4 bokstaver");
	       do {
	    	   bnavn = leser.nextLine();
	    	   
	    	   
	       }while(bnavn.length() < 4 && bnavn.length() > 3);
	       
	       System.out.println("Skriv inn fornavn: ");
	       String fornavn = leser.nextLine();
	       
	       System.out.println("Skriv inn etternavn: ");
	       String etternavn = leser.nextLine();
	       
	       LocalDate ansDato = null;
	       
	       do {
	    	   System.out.println("skriv ansettelsedato (YYYY-MM-DD) :");
	    	   try {
	    		   ansDato = LocalDate.parse(leser.nextLine());
	    	   }catch(DateTimeParseException e) {
	    		   System.out.println("Feil dato-format, skriv YYYY-MM-DD feks" + LocalDate.now());
	    	   }
	       }while(ansDato == null);
	       
	       System.out.println("skriv inn stillingstittel");
	       String stilling = leser.nextLine();
	       
	       int loenn = 0;
	       do {
	    	   System.out.println("Skriv inn månedslønn");
	    	   loenn = leser.nextInt();
	    	   leser.nextLine();
	       }while(loenn == 0);
	       System.out.println("Velg avdeling");
	       System.out.println(" 1 : Styre");
	       System.out.println(" 2 : Renhold \n");
	       System.out.println("Skriv inn avdelingid");
	       int avdId = leser.nextInt();
	       Avdeling avd = avdelingEAO.finnAvdelingmedId(avdId);
	       leser.nextLine();
	       
	       
	       
	    Ansatt nyAnsatt = new Ansatt(bnavn, fornavn, etternavn, ansDato, stilling, loenn, avd);
		return nyAnsatt;
	}
	
	//Metoder for Avdeling
	
	public static void skrivUtAvdelingMedId(int avdelingId) {
		System.out.println("Skriver ut avdeling: \n" + avdelingEAO.finnAvdelingmedId(avdelingId));
	}
	
	public static void skrivUtAvdeling(int id) {
		List<Ansatt> ansatte = avdelingEAO.utlistingAnsatteVedAvdeling(id);
		Iterator<Ansatt> iter = ansatte.iterator();
		while(iter.hasNext()) {
			Ansatt ansatt = iter.next();
			if(erSjef(ansatt)) {
				System.out.println("*" + ansatt +  "*");
			}else {
				System.out.println(ansatt);
			}
		}
	}
	
	public static void oppdaterAvdelingForAnsatt(int ansattId, Avdeling nyAvdelingId) {

		Ansatt a = ansattEAO.finnAnsattMedId(ansattId);
		if (a != null) {
			if (!erSjef(a)) {
				a.setAvd(nyAvdelingId);
				avdelingEAO.oppdaterAvdeling(a.getAvd());
				System.out.println("Ansatt " + a.getAvd() + ", " + a.getBrukernavn() + " har fått ny avdeling: "
						+ avdelingEAO.finnAvdelingmedId(nyAvdelingId.getAvdId()).getNavn());
			} else {
				System.out.println(
						"Kan ikke oppdatere avdeling fordi " + a.getBrukernavn() + " er sjef på en annen avdeling.");
			}
		}else {
			System.out.println("Kunne ikke finne ansatt.");
		}
	}
	
	public static void leggTilAvdeling() {
		
    System.out.println("Legger til ny ansatt");
	System.out.println("Skriv søkestreng: ID/Brukernavn på sjef i den nye avdelingen");
	int id = Integer.parseInt(leser.nextLine());
	Ansatt funnet = ansattEAO.finnAnsattMedId(id);
	if(funnet == null) {
		System.out.println("Fant ikke den ansatte");
	}
	
	if (!erSjef(funnet)) {
		System.out.println("Skriv inn navn på ny avdeling");
		String avdelingsNavn = leser.nextLine();
		Avdeling a = new Avdeling(avdelingsNavn, funnet);
		avdelingEAO.leggTilNyAvdeling(a);
		System.out.println("Ny avdeling lagt til");
		
	} else {
		System.out.println("Kan ikke oppdatere avdeling fordi " + funnet.getBrukernavn() + " er sjef på en annen avdeling.");
	}
	}
	
	// Hjelpemetoder
	private static boolean erSjef(Ansatt a) {
		boolean sjef = false;
		Avdeling avd = avdelingEAO.finnAvdelingmedId(a.getAvd().getAvdId());
		if(avd.getSjef().getAnsattId() == a.getAnsattId() && avd != null) {
			sjef = true;
		}
		return sjef;
	}
	

	

}

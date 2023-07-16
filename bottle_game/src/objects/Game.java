package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	List<Integer> colori;
	List<Bottle> bottiglie;
	int[]filler = new int[4];
	Scanner sc=new Scanner(System.in);
	
	Game(){
		newMatch();
		
		while(!victory()) {
			turnSelector();
		}
	}
	
	
	/**
	 * controlla se tutte le bottiglie sono ugualmente riempite
	 * @return
	 */
	private boolean victory() {
		for (int i=0; i<bottiglie.size(); i++) {
			if(!bottiglie.get(i).allItemsEquals()) {
				return false;
			}
		}
		System.out.println("complimenti, hai vinto!!");
		return true;
	}



	/**
	 * genera una nuova plancia di gioco e resetta quelle precedenti
	 */
	void newMatch() {
		colorFiller();
		startBottleFiller();
		
	}
	
	void colorFiller(){
		 colori= new ArrayList<Integer>();
		 for (int coloriDiversi=1; coloriDiversi<=9; coloriDiversi++) {
			 for(int i=0; i<4; i++) {
				 colori.add(coloriDiversi);
			 }
		 }
	}
	
	/**
	 * crea dei numeri randomici e assegna a ciascuna bottiglia un numero da 1 a 9 rappresentante i colori diversi
	 */
	void startBottleFiller() {
		Random r=new Random();
		bottiglie=new ArrayList<Bottle>();
		for(int j=1; j<=9; j++) {
			for(int i=0; i<4; i++) {
				int numeroEstratto=r.nextInt(0, colori.size());
				filler[i]=colori.get(numeroEstratto);
				colori.remove(numeroEstratto);
			}
			Bottle b=new Bottle(j, filler);
			bottiglie.add(b);
		}
		
		//void bottle
		for (int i=0; i<4; i++) {
			filler[i]=0;
		}
		Bottle b=new Bottle(10, filler);
		bottiglie.add(b);
	}
	

	
	void turnSelector() {
		printTurn();
		System.out.println("\n\nselezionare una bottiglia di partenza da 1 a 10:\t");
		sc.reset();
		int startBottle=sc.nextInt()-1;
		if(startBottle<0 || startBottle>9) {
			System.out.println("seleziona un elemento valido");
			return;
		}
		System.out.println("selezionare una bottiglia di destinaz da 1 a 10:\t");
		sc.reset();
		int finBottle=sc.nextInt()-1;
		
		//la bottiglia deve essere nell'array
		if(finBottle<0 || finBottle>9) {
			System.out.println("seleziona un elemento valido");
			return;
		}
		//la bottiglia finaledeve essere diversa da quella di partenza
		if(finBottle==startBottle) {
			System.out.println("non puoi selezionare la stessa bottiglia");
			return;
		}
		//inizializzazione variabili
		Bottle s=bottiglie.get(startBottle);
		Bottle f=bottiglie.get(finBottle);
		
		f.fillBottle(s.topColor(), s.countRemoveTopItems(f.countTopItemsFree(), s.topColor()));
	}
	
	/**
	 * stampa una lista di bottiglie (ciascuna bottiglia occupa una riga, l'elemento
	 * più in alto, quindi quello prendibile è a destra
	 */
	void printTurn() {
		for (int i = 0; i < bottiglie.size(); i++) {
			bottiglie.get(i).printBottle();
		}
	}
}
